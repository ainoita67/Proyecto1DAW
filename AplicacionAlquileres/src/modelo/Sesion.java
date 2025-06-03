package modelo;

/**
 * Clase que gestiona la sesión activa en el sistema.
 * Permite almacenar y acceder al usuario actualmente autenticado, así como al rol activo.
 * 
 * Todos los métodos y atributos son estáticos, ya que la sesión es global para la aplicación.
 * 
 * @author Pyto_Grupo_D
 * @version 1.0
 */
public class Sesion {

    /**
     * Usuario que ha iniciado sesión actualmente.
     */
    private static Usuario usuarioActivo;

    /**
     * Método que establece el usuario que ha iniciado sesión.
     * 
     * @param usuario Usuario activo.
     */
    public static void setUsuarioActivo(Usuario usuario) {
        usuarioActivo = usuario;
    }

    /**
     * Método que devuelve el usuario que ha iniciado sesión.
     * 
     * @return Usuario activo.
     */
    public static Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    /**
     * Método que cierra la sesión del usuario actual.
     * Establece el usuario activo como {@code null}.
     */
    public static void cerrarSesionUsuario() {
        usuarioActivo = null;
    }
}

