package bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

import modelo.Furgoneta;
import modelo.Moto;
import modelo.Turismo;
import modelo.Vehiculo;

/**
 * @author Pyto_Grupo_D
 * @version 1.0
 * 
 * Clase que gestiona las operaciones CRUD sobre vehículos (Turismo, Furgoneta, Moto).
 * Incluye operaciones avanzadas como filtrado por disponibilidad y generación de objetos según tipo.
 */
public class DbVehiculo extends Conexion {

    /**
     * Constructor que establece la conexión con la base de datos.
     * @throws SQLException si ocurre un error de conexión.
     */
    public DbVehiculo() throws SQLException {
        super();
    }

    /**
     * Método que muestra una lista de vehículos disponibles o no entre dos fechas.
     * @param fecha1 Fecha inicial.
     * @param fecha2 Fecha final.
     * @param soloDisponibles {@code true} si solo se deben traer los vehículos no alquilados en ese período.
     * @return Lista de vehículos que cumplen con los criterios.
     */
    public ArrayList<Vehiculo> obtenerVehiculos(LocalDate fecha1, LocalDate fecha2, boolean soloDisponibles) {
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT v.matricula, v.modelo, v.marca, v.precioh, v.fecha_matriculacion, v.color, v.plazas, v.tipo, \n"
            + "v.tipo_turismo, v.tipo_furgo, f.frecuencia, \n"
            + "IF((SELECT MAX(m.fecha) FROM mantenimiento m WHERE m.matricula = v.matricula) IS NOT NULL,\n"
            + "(SELECT MAX(m.fecha) FROM mantenimiento m WHERE m.matricula = v.matricula),\n"
            + "v.fecha_matriculacion) AS fecha_ultimo_mantenimiento\n"
            + "FROM vehiculo v\n"
            + "JOIN tipo t ON v.tipo = t.id\n"
            + "JOIN fechasmant f ON f.tipo = t.id\n"
            + "WHERE TIMESTAMPDIFF(YEAR, v.fecha_matriculacion, CURDATE()) >= f.desde\n"
            + "AND TIMESTAMPDIFF(YEAR, v.fecha_matriculacion, CURDATE()) < f.hasta\n"
        );

        if (soloDisponibles) {
            sql.append(
                "AND NOT EXISTS (\n"
                + "SELECT 1 FROM alquiler a\n"
                + "WHERE a.vehiculo = v.matricula AND (\n"
                + "a.fechaini BETWEEN ? AND ? OR a.fechafin BETWEEN ? AND ?\n"
                + "OR (? BETWEEN a.fechaini AND a.fechafin)\n"
                + "OR (? BETWEEN a.fechaini AND a.fechafin))\n"
                + ")\n"
            );
        }

        try (PreparedStatement stmt = conexion.prepareStatement(sql.toString())) {
            if (soloDisponibles) {
                java.sql.Date sqlFecha1 = java.sql.Date.valueOf(fecha1);
                java.sql.Date sqlFecha2 = java.sql.Date.valueOf(fecha2);
                stmt.setDate(1, sqlFecha1);
                stmt.setDate(2, sqlFecha2);
                stmt.setDate(3, sqlFecha1);
                stmt.setDate(4, sqlFecha2);
                stmt.setDate(5, sqlFecha1);
                stmt.setDate(6, sqlFecha2);
            }

            ResultSet rs = stmt.executeQuery();
            return genObjetosyArray(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaVehiculos;
    }

    /**
     * Método que bsuca un único vehículo a partir de su matrícula.
     * @param matricula Matrícula del vehículo a consultar.
     * @return Objeto {@link Vehiculo} o {@code null} si no se encuentra.
     */
    public Vehiculo ver1Vehiculo(String matricula) {
        String sql = "SELECT matricula, modelo, marca, precioh, fecha_matriculacion, color, plazas, v.tipo, tipo_turismo, tipo_furgo, frecuencia, \n"
                   + "IF((SELECT MAX(m.fecha) FROM mantenimiento m WHERE m.matricula = v.matricula) IS NOT NULL,\n"
                   + "(SELECT MAX(m.fecha) FROM mantenimiento m WHERE m.matricula = v.matricula),\n"
                   + "v.fecha_matriculacion) AS fecha_ultimo_mantenimiento\n"
                   + "FROM vehiculo v \n"
                   + "JOIN tipo t ON v.tipo = t.id \n"
                   + "JOIN fechasmant f ON f.tipo = t.id \n"
                   + "WHERE TIMESTAMPDIFF(YEAR, fecha_matriculacion, CURDATE()) >= f.desde AND TIMESTAMPDIFF(YEAR, fecha_matriculacion, CURDATE()) < f.hasta AND matricula = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                double precioh = rs.getDouble("precioh");
                LocalDate fmatriculacion = rs.getDate("fecha_matriculacion").toLocalDate();
                String color = rs.getString("color");
                int plazas = rs.getInt("plazas");
                int tipo = rs.getInt("tipo");
                String tipo_turismo = rs.getString("tipo_turismo");
                String tipo_furgo = rs.getString("tipo_furgo");
                int frecuencia = rs.getInt("frecuencia");
                LocalDate fmant = rs.getDate("fecha_ultimo_mantenimiento").toLocalDate();
                LocalDate proxMant = fmant.plusMonths(frecuencia);

                if (tipo == 1) return new Turismo(matricula, marca, modelo, precioh, fmatriculacion, proxMant, color, plazas, tipo_turismo);
                if (tipo == 2) return new Furgoneta(matricula, marca, modelo, precioh, fmatriculacion, proxMant, color, plazas, tipo_furgo);
                if (tipo == 3) return new Moto(matricula, marca, modelo, precioh, fmatriculacion, proxMant, color, plazas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Métedo que actualiza la información de un vehículo existente.
     * @param vehiculo Objeto {@link Vehiculo} con los datos a actualizar.
     * @return {@code true} si la actualización fue exitosa.
     */
    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculo SET modelo = ?, marca = ?, precioh = ?, fecha_matriculacion = ?, color = ?, plazas = ?, tipo = ?, tipo_turismo = ?, tipo_furgo = ? WHERE matricula = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, vehiculo.getModelo());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setDouble(3, vehiculo.getPrecioH());
            stmt.setDate(4, java.sql.Date.valueOf(vehiculo.getF_matriculacion()));
            stmt.setString(5, vehiculo.getColor());
            stmt.setInt(6, vehiculo.getPlazas());

            if (vehiculo instanceof Turismo) {
                stmt.setInt(7, 1);
                stmt.setString(8, ((Turismo) vehiculo).getTipo());
                stmt.setNull(9, java.sql.Types.VARCHAR);
            } else if (vehiculo instanceof Furgoneta) {
                stmt.setInt(7, 2);
                stmt.setNull(8, java.sql.Types.VARCHAR);
                stmt.setString(9, ((Furgoneta) vehiculo).getTipo());
            } else if (vehiculo instanceof Moto) {
                stmt.setInt(7, 3);
                stmt.setNull(8, java.sql.Types.VARCHAR);
                stmt.setNull(9, java.sql.Types.VARCHAR);
            } else {
                return false;
            }

            stmt.setString(10, vehiculo.getMatricula());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que crea un nuevo vehículo en la base de datos.
     * @param vehiculo Objeto {@link Vehiculo} a registrar.
     * @return {@code true} si la inserción fue exitosa.
     */
    public boolean crearVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculo (matricula, modelo, marca, precioh, fecha_matriculacion, color, plazas, tipo, tipo_turismo, tipo_furgo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getModelo());
            stmt.setString(3, vehiculo.getMarca());
            stmt.setDouble(4, vehiculo.getPrecioH());
            stmt.setDate(5, java.sql.Date.valueOf(vehiculo.getF_matriculacion()));
            stmt.setString(6, vehiculo.getColor());
            stmt.setInt(7, vehiculo.getPlazas());

            if (vehiculo instanceof Turismo) {
                stmt.setInt(8, 1);
                stmt.setString(9, ((Turismo) vehiculo).getTipo());
                stmt.setNull(10, java.sql.Types.VARCHAR);
            } else if (vehiculo instanceof Furgoneta) {
                stmt.setInt(8, 2);
                stmt.setNull(9, java.sql.Types.VARCHAR);
                stmt.setString(10, ((Furgoneta) vehiculo).getTipo());
            } else if (vehiculo instanceof Moto) {
                stmt.setInt(8, 3);
                stmt.setNull(9, java.sql.Types.VARCHAR);
                stmt.setNull(10, java.sql.Types.VARCHAR);
            } else {
                return false;
            }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que elimina un vehículo de la base de datos.
     * @param matricula Matrícula del vehículo a eliminar.
     * @return {@code true} si se eliminó correctamente.
     */
    public boolean eliminarVehiculo(String matricula) {
        String sql = "DELETE FROM vehiculo WHERE matricula = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que convierte un {@link ResultSet} en una lista de objetos {@link Vehiculo}.
     * @param rs ResultSet obtenido de una consulta SQL.
     * @return Lista de vehículos construida a partir del ResultSet.
     */
    public ArrayList<Vehiculo> genObjetosyArray(ResultSet rs) {
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

        try {
            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                double precioh = rs.getDouble("precioh");
                LocalDate fmatriculacion = rs.getDate("fecha_matriculacion").toLocalDate();
                String color = rs.getString("color");
                int plazas = rs.getInt("plazas");
                int tipo = rs.getInt("tipo");
                String tipo_turismo = rs.getString("tipo_turismo");
                String tipo_furgo = rs.getString("tipo_furgo");
                int frecuencia = rs.getInt("frecuencia");
                LocalDate fmant = rs.getDate("fecha_ultimo_mantenimiento").toLocalDate();
                LocalDate proxMant = fmant.plusMonths(frecuencia);

                if (tipo == 1) {
                    listaVehiculos.add(new Turismo(matricula, marca, modelo, precioh, fmatriculacion, proxMant, color, plazas, tipo_turismo));
                } else if (tipo == 2) {
                    listaVehiculos.add(new Furgoneta(matricula, marca, modelo, precioh, fmatriculacion, proxMant, color, plazas, tipo_furgo));
                } else if (tipo == 3) {
                    listaVehiculos.add(new Moto(matricula, marca, modelo, precioh, fmatriculacion, proxMant, color, plazas));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaVehiculos;
    }
}
