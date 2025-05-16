package bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Alquiler;
import modelo.Cliente;
import modelo.Vehiculo;


public class DbAlquileres extends Conexion {
    public DbAlquileres() throws SQLException {
        super();
    }

    public ArrayList<Alquiler> obtenerAlquileres() {
        return obtenerAlquileresConFiltro(null, null);
    }

    public ArrayList<Alquiler> obtenerAlquileresPorCliente(String dniCliente) {
        return obtenerAlquileresConFiltro("cliente", dniCliente);
    }

    public ArrayList<Alquiler> obtenerAlquileresPorVehiculo(String matriculaVehiculo) {
        return obtenerAlquileresConFiltro("vehiculo", matriculaVehiculo);
    }

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
}
