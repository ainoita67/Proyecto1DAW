package bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Alquiler;
import modelo.Cliente;
import modelo.Vehiculo;

/**
 * Clase que gestiona las operaciones CRUD relacionadas con los alquileres.
 * Hereda de {@link Conexion} para hacer la conexión a la base de datos.
 * 
 * @author Pyto_Grupo_D
 * @version 1.0
 */
public class DbAlquileres extends Conexion {
	
	/**
     * Constructor que hace la conexión a la base de datos.
     * @throws SQLException si ocurre un error al conectar.
     */
    public DbAlquileres() throws SQLException {
        super();
    }
    
    /**
     * Método que obtiene todos los registros de alquileres en la base de datos.
     * @return Lista de objetos {@link Alquiler}.
     */
    public ArrayList<Alquiler> obtenerAlquileres() {
        return obtenerAlquileresConFiltro(null, null);
    }
    
    /**
     * Método que obtiene los registros de alquileres asociados a un cliente.
     * @param dniCliente DNI del cliente.
     * @return Lista de alquileres del cliente.
     */
    public ArrayList<Alquiler> obtenerAlquileresPorCliente(String dniCliente) {
        return obtenerAlquileresConFiltro("cliente", dniCliente);
    }
    
    /**
     * Método obtiene los registros de alquileres asociados a un vehículo.
     * @param matriculaVehiculo Matrícula del vehículo.
     * @return Lista de alquileres del vehículo.
     */
    public ArrayList<Alquiler> obtenerAlquileresPorVehiculo(String matriculaVehiculo) {
        return obtenerAlquileresConFiltro("vehiculo", matriculaVehiculo);
    }
    
    /**
     * Método privado que obtiene los alquileres desde la base de datos con o sin filtro.
     * @param campo Campo por el que filtrar ("cliente" o "vehiculo").
     * @param valor Valor del campo a filtrar.
     * @return Lista de objetos {@link Alquiler} filtrados o no.
     */
    private ArrayList<Alquiler> obtenerAlquileresConFiltro(String campo, String valor) {
        ArrayList<Alquiler> listaAlquileres = new ArrayList<>();

        String baseSQL = "SELECT cliente, vehiculo, fechaini, fechafin, total FROM alquiler";
        String sql = (campo != null && valor != null) ? baseSQL + " WHERE " + campo + " = ?" : baseSQL;
        sql += " ORDER BY fechaini";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            if (campo != null && valor != null) {
                stmt.setString(1, valor);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                DbCliente dbCliente = new DbCliente();
                DbVehiculo dbVehiculo = new DbVehiculo();

                while (rs.next()) {
                    String dni = rs.getString("cliente");
                    String matricula = rs.getString("vehiculo");
                    LocalDate fechaIni = rs.getDate("fechaini").toLocalDate();
                    LocalDate fechaFin = rs.getDate("fechafin").toLocalDate();
                    double total = rs.getDouble("total");

                    Cliente cliente = dbCliente.ver1Cliente(dni);
                    Vehiculo vehiculo = dbVehiculo.ver1Vehiculo(matricula);

                    if (cliente != null && vehiculo != null) {
                        Alquiler alquiler = new Alquiler(cliente, vehiculo, fechaIni, fechaFin, total);
                        listaAlquileres.add(alquiler);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAlquileres;
    }
    
    /**
     * Método que inserta un nuevo alquiler en la base de datos.
     * @param alquiler Objeto {@link Alquiler} a insertar.
     * @return {@code true} si se insertó correctamente, {@code false} en caso contrario.
     */
    public boolean crearAlquiler(Alquiler alquiler) {
        String sql = "INSERT INTO alquiler (cliente, vehiculo, fechaini, fechafin, total) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, alquiler.getCliente().getDNI());
            stmt.setString(2, alquiler.getVehiculo().getMatricula());
            stmt.setDate(3, java.sql.Date.valueOf(alquiler.getFecha_ini()));
            stmt.setDate(4, java.sql.Date.valueOf(alquiler.getFecha_fin()));
            stmt.setDouble(5, alquiler.getTotal());

            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que elimina un alquiler específico de la base de datos.
     * @param dniCliente DNI del cliente asociado al alquiler.
     * @param matriculaVehiculo Matrícula del vehículo alquilado.
     * @param fechaini Fecha de inicio del alquiler.
     * @return {@code true} si se eliminó correctamente, {@code false} en caso contrario.
     */
    public boolean eliminarAlquiler(String dniCliente, String matriculaVehiculo, LocalDate fechaini) {
        String sql = "DELETE FROM alquiler WHERE cliente = ? AND vehiculo = ? AND date(fechaini) = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dniCliente);
            stmt.setString(2, matriculaVehiculo);
            stmt.setDate(3, java.sql.Date.valueOf(fechaini));

            System.out.println("Intentando eliminar con: DNI = " + dniCliente + 
                               ", Matrícula = " + matriculaVehiculo + 
                               ", Fecha Inicio = " + fechaini);

            int filas = stmt.executeUpdate();
            System.out.println("Filas eliminadas: " + filas);
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL al eliminar:");
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Método que saca una lista de los alquileres que hay hoy en la base de datos.
     * @return Lista de objetos {@link Alquiler} de hoy.
     */

    public ArrayList<Alquiler> obtenerAlquileresHoy() {
        ArrayList<Alquiler> listaAlquileres = new ArrayList<>();
        String sql = "SELECT cliente, vehiculo, fechaini, fechafin, total FROM alquiler WHERE fechaini <= ? AND fechafin >= ? ORDER BY fechaini";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            java.sql.Date hoy = java.sql.Date.valueOf(LocalDate.now());
            stmt.setDate(1, hoy);
            stmt.setDate(2, hoy);

            try (ResultSet rs = stmt.executeQuery()) {
                DbCliente dbCliente = new DbCliente();
                DbVehiculo dbVehiculo = new DbVehiculo();

                while (rs.next()) {
                    String dni = rs.getString("cliente");
                    String matricula = rs.getString("vehiculo");
                    LocalDate fechaIni = rs.getDate("fechaini").toLocalDate();
                    LocalDate fechaFin = rs.getDate("fechafin").toLocalDate();
                    double total = rs.getDouble("total");

                    Cliente cliente = dbCliente.ver1Cliente(dni);
                    Vehiculo vehiculo = dbVehiculo.ver1Vehiculo(matricula);

                    if (cliente != null && vehiculo != null) {
                        Alquiler alquiler = new Alquiler(cliente, vehiculo, fechaIni, fechaFin, total);
                        listaAlquileres.add(alquiler);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAlquileres;
    }

}
