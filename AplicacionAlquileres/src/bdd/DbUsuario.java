package bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Usuario;

/**
 * Clase que gestiona las operaciones CRUD sobre la entidad Usuario.
 * Usa la tabla 'usuario' en la base de datos, considerando diferentes roles.
 * Filtra usando el campo 'rol', 2 para empleados y 3 para administradores.
 * 
 * @author Pyto_Grupo_D
 * @version 1.0
 */
public class DbUsuario extends Conexion {

    /**
     * Constructor que establece la conexión con la base de datos.
     * @throws SQLException si ocurre un error al conectar.
     */
    public DbUsuario() throws SQLException {
        super();
    }

    /**
     * Método que crea un nuevo usuario en la base de datos.
     * @param usuario Objeto {@link Usuario} con los datos a insertar.
     * @return {@code true} si se insertó correctamente, {@code false} si hubo un error.
     */
    public boolean crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (dni, nombre, telef, correo, direccion, contrasea, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getDNI());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getTfno());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getDireccion());
            stmt.setString(6, usuario.getContrasea());
            stmt.setInt(7, usuario.getRol());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que muestra todos los usuarios de un rol específico.
     * @param rol Rol que se desea consultar (por ejemplo, 1 para clientes).
     * @return Lista de objetos {@link Usuario}.
     */
    public ArrayList<Usuario> verTodosUsuarios(int rol) {
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        String sql = "SELECT dni, nombre, telef, correo, direccion, contrasea, rol FROM usuario WHERE rol = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, rol);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listaUsuario.add(new Usuario(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("telef"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getString("contrasea"),
                        rs.getInt("rol")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuario;
    }

    /**
     * Método que actualiza los datos de un usuario existente.
     * @param usuario Objeto {@link Usuario} con los nuevos datos.
     * @return {@code true} si la actualización fue exitosa, {@code false} si hubo un error.
     */
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre=?, correo=?, telef=?, direccion=?, contrasea=?, rol=? WHERE dni=?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getTfno());
            stmt.setString(4, usuario.getDireccion());
            stmt.setString(5, usuario.getContrasea());
            stmt.setInt(6, usuario.getRol());
            stmt.setString(7, usuario.getDNI());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que elimina un usuario según su DNI y rol.
     * @param dni DNI del usuario.
     * @param rol Rol del usuario.
     * @return {@code true} si se eliminó correctamente, {@code false} en caso contrario.
     */
    public boolean eliminarUsuario(String dni, int rol) {
        String sql = "DELETE FROM usuario WHERE dni = ? AND rol = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.setInt(2, rol);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método que devuelve un usuario por su DNI, sin filtrar por rol.
     * @param dni DNI del usuario.
     * @return Objeto {@link Usuario} si se encuentra, o {@code null} si no.
     */
    public Usuario ver1Usuario(String dni) {
        String sql = "SELECT dni, nombre, telef, correo, direccion, contrasea, rol FROM usuario WHERE dni = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("telef"),
                    rs.getString("correo"),
                    rs.getString("direccion"),
                    rs.getString("contrasea"),
                    rs.getInt("rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método que devuelve un usuario por su DNI y rol.
     * @param dni DNI del usuario.
     * @param rol Rol del usuario.
     * @return Objeto {@link Usuario} si se encuentra, o {@code null} si no.
     */
    public Usuario ver1Usuario(String dni, int rol) {
        String sql = "SELECT dni, nombre, telef, correo, direccion, contrasea, rol FROM usuario WHERE dni = ? AND rol = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.setInt(2, rol);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("telef"),
                    rs.getString("correo"),
                    rs.getString("direccion"),
                    rs.getString("contrasea"),
                    rs.getInt("rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método que verifica si las credenciales de un usuario (DNI y contraseña) son válidas.
     * @param dni DNI del usuario.
     * @param password Contraseña del usuario.
     * @return {@code true} si las credenciales son válidas, {@code false} en caso contrario.
     */
    public static boolean verificarCredenciales(String dni, String password) {
        Conexion db = null;

        try {
            db = new Conexion();
            String sql = "SELECT * FROM usuario WHERE dni = ? AND contrasea = ?";
            PreparedStatement ps = db.conexion.prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            return false;
        } finally {
            if (db != null) {
                db.cerrar();
            }
        }
    }
}

