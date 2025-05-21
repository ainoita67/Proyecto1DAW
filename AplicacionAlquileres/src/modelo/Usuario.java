package modelo;

/**
 * Clase que gestiona los datos del objeto usuario.
 * Un usuario tiene información personal y un rol asignado (administrador, empleado, etc.).
 * 
 * Esta clase permite gestionar la información del usuario, cambiar su rol y verificar sus privilegios.
 * 
 * @author Pyto_Grupo_D
 * @version 1.0
 */
public class Usuario {
    
	/** DNI único del cliente.*/
    private final String DNI;
    /** Nombre completo del usuario.*/
    private String nombre;
    /** Número telefonico del usuario.*/
    private String tfno;
    /** Correo electrónico del usuario.*/
    private String correo;
    /** Dirección del usuario.*/
    private String direccion;
    /** Contraseña del usuario para la autenticación.*/
    private String contrasea;
    /** Rol del usuario (1 - Cliente, 2 - Empleado, 3 - Administrador).*/
    private int rol;
    
    /**
     * Constructor para crear un nuevo usuario con los datos proporcionados.
     * 
     * @param dNI Documento Nacional de Identidad del usuario.
     * @param nombre Nombre completo del usuario.
     * @param tfno Número de teléfono del usuario.
     * @param correo Correo electrónico del usuario.
     * @param direccion Dirección física del usuario.
     * @param contrasea Contraseña del usuario para la autenticación.
     * @param rol Rol del usuario (1 - Cliente, 2 - Empleado, 3 - Administrador, etc.).
     */
    public Usuario(String dNI, String nombre, String tfno, String correo, String direccion, String contrasea, int rol) {
        super();
        DNI = dNI;
        this.nombre = nombre;
        this.tfno = tfno;
        this.correo = correo;
        this.direccion = direccion;
        this.contrasea = contrasea;
        this.rol = rol;
    }
    
    /**
     * Método que obtiene el nombre del usuario.
     * 
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método que establece el nombre del usuario.
     * 
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Método que obtiene el número de teléfono del usuario.
     * 
     * @return Número de teléfono del usuario.
     */
    public String getTfno() {
        return tfno;
    }
    
    /**
     * Método que establece el número de teléfono del usuario.
     * 
     * @param tfno Número de teléfono del usuario.
     */
    public void setTfno(String tfno) {
        this.tfno = tfno;
    }
    
    /**
     * Método que obtiene el correo electrónico del usuario.
     * 
     * @return Correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }
    
    /**
     * Método que establece el correo electrónico del usuario.
     * 
     * @param correo Correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Método que obtiene la dirección del usuario.
     * 
     * @return Dirección del usuario.
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Método que establece la dirección del usuario.
     * 
     * @param direccion Dirección del usuario.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Método que obtiene la contraseña del usuario.
     * 
     * @return Contraseña del usuario.
     */
    public String getContrasea() {
        return contrasea;
    }
    
    /**
     * Método que establece la contraseña del usuario.
     * 
     * @param contrasea Contraseña del usuario.
     */
    public void setContrasea(String contrasea) {
        this.contrasea = contrasea;
    }
    
    /**
     * Método que obtiene el DNI del usuario.
     * 
     * @return DNI del usuario.
     */
    public String getDNI() {
        return DNI;
    }
    
    /**
     * Método que establece el rol del usuario.
     * 
     * @param rol Rol del usuario (1 - Cliente, 2 - Empleado, 3 - Administrador, etc.).
     */
    public void setRol(int rol) {
        this.rol = rol;
    }
    
    /**
     * Método que obtiene el rol del usuario.
     * 
     * @return Rol del usuario (1 - Cliente, 2 - Empleado, 3 - Administrador, etc.).
     */
    public int getRol() {
        return rol;
    }
    
    /**
     * Método que verifica si el usuario tiene rol de administrador.
     * 
     * @return {@code true} si el rol es de administrador, {@code false} en caso contrario.
     */
    public boolean isAdmin() {
        return rol == 3;
    }
    
    /**
     * Método que verifica si el usuario tiene rol de empleado.
     * 
     * @return {@code true} si el rol es de empleado, {@code false} en caso contrario.
     */
    public boolean isEmpleado() {
        return rol == 2;
    }
    
    /**
     * Método que establece el rol de administrador si se selecciona {@code true}.
     * 
     * @param selected Si {@code true}, se asigna el rol de administrador al usuario.
     */
    public void setAdmin(boolean selected) {
        if (selected) {
            this.rol = 3;
        }
    }
    
    /**
     * Método que establece el rol de empleado si se selecciona {@code true}.
     * 
     * @param selected Si {@code true}, se asigna el rol de empleado al usuario.
     */
    public void setEmpleado(boolean selected) {
        if (selected) {
            this.rol = 2;
        }
    }
}

