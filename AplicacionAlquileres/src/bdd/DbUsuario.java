package bdd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Usuario;
public class DbUsuario extends Conexion {
    public DbUsuario() throws SQLException {
        super();
    }
    
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
    
    public ArrayList<Usuario> verTodosUsuarios(int rol) {
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        String sql = "SELECT dni, nombre, telef, correo, direccion, contrasea, rol FROM usuario WHERE rol = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, rol);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listaUsuario.add(new Usuario(rs.getString("dni"), rs.getString("nombre"), rs.getString("telef"), rs.getString("correo"), rs.getString("direccion"), rs.getString("contrasea"), rs.getInt("rol")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuario;
    }
    
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
    
    public Usuario ver1Usuario(String dni) {
        String sql = "SELECT dni, nombre, telef, correo, direccion, contrasea, rol FROM usuario WHERE dni = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getString("dni"), rs.getString("nombre"), rs.getString("telef"), rs.getString("correo"), rs.getString("direccion"), rs.getString("contrasea"), rs.getInt("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Usuario ver1Usuario(String dni, int rol) {
        String sql = "SELECT dni, nombre, telef, correo, direccion, contrasea, rol FROM usuario WHERE dni = ? AND rol = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.setInt(2, rol);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getString("dni"), rs.getString("nombre"), rs.getString("telef"), rs.getString("correo"), rs.getString("direccion"), rs.getString("contrasea"), rs.getInt("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
