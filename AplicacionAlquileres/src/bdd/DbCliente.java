package bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;

/**
 * Clase que gestiona las operaciones CRUD relacionadas con los clientes.
 * Filtra por clientes usando el campo 'rol', siendo 1 para clientes, 
 * 2 para empleados y 3 para administradores.
 * 
 * @author Pyto_Grupo_D
 * @version 1.0
 */
public class DbCliente extends Conexion {

    /**
     * Constructor que establece la conexión con la base de datos.
     * @throws SQLException si ocurre un error al conectar.
     */
    public DbCliente() throws SQLException {
        super();
    }

    /**
     * Método que inserta un nuevo cliente en la base de datos.
     * @param cliente Objeto {@link Cliente} con los datos a guardar.
     * @return {@code true} si se insertó correctamente, {@code false} si hubo un error.
     */
    public boolean crearCliente(Cliente cliente) {
        String sql = "INSERT INTO usuario (dni, nombre, telef, correo, direccion, rol) VALUES (?, ?, ?, ?, ?, 1)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getDNI());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getTfno());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getDireccion());
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  Método que busca y devuelve un cliente por su DNI.
     * @param dni DNI del cliente a buscar.
     * @return Objeto {@link Cliente} si se encuentra, o {@code null} si no.
     */
    public Cliente ver1Cliente(String dni) {
        String sql = "SELECT dni, nombre, telef, correo, direccion FROM usuario WHERE dni = ? and rol = 1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                String dniCliente = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String telef = rs.getString("telef");
                String correo = rs.getString("correo");
                String direccion = rs.getString("direccion");

                return new Cliente(dniCliente, nombre, telef, correo, direccion);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  Método que muestra todos los clientes almacenados en la base de datos.
     * @return Lista de objetos {@link Cliente}.
     */
    public ArrayList<Cliente> verTodosClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT dni, nombre, telef, correo, direccion FROM usuario WHERE rol = 1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String dniCliente = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String telef = rs.getString("telef");
                String correo = rs.getString("correo");
                String direccion = rs.getString("direccion");

                Cliente cliente = new Cliente(dniCliente, nombre, telef, correo, direccion);
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }

    /**
     *  Método actualiza los datos de un cliente existente.
     * @param cliente Objeto {@link Cliente} con los nuevos datos.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE usuario SET nombre=?, correo=?, telef=?, direccion=? WHERE dni=? AND rol=1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getCorreo());
            stmt.setString(3, cliente.getTfno());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getDNI());

            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  Método que elimina un cliente de la base de datos.
     * @param dni DNI del cliente a eliminar.
     * @return {@code true} si se eliminó correctamente, {@code false} si no se encontró o falló.
     */
    public boolean eliminarCliente(String dni) {
        String sql = "DELETE FROM usuario WHERE dni = ? AND rol = 1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

