package modelo;

public class Sesion {
    private static Usuario usuarioActivo;

    public static void setUsuarioActivo(Usuario usuario) {
        usuarioActivo = usuario;
    }

    public static Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public static void cerrarSesionUsuario() {
        usuarioActivo = null;
    }
    
    private static Usuario rolActivo;

    public static void setrolActivo(Usuario rol) {
    	rolActivo = rol;
    }

    public static Usuario getrolActivo() {
        return rolActivo;
    }

    public static void cerrarSesionRol() {
    	rolActivo = null;
    }
}
