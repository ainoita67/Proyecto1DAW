package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pyto_Grupo_D
 * @version 1.0
 * 
 * Clase que gestiona la conexión con la base de datos MySQL, 
 * utilizando JDBC para establecer y cerrar la conexión.
 * 
 */
public class Conexion {
	
	/** Dirección IP del servidor MySQL. */
 	private static final String DB_HOST = "192.168.4.140";
 	/** Puerto con el que se conecta a MySQL. */
    private static final String DB_PORT = "3306";
    /** Nombre de la base de datos */
    private static final String DB_NAME = "alquiler";
    /** URL completa de la conexión JDBC. */
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
    /** Usuario de la base de datos. */
    private static final String DB_USER = "root";
    /** Contraseña del usuario de la base de datos. */
    private static final String DB_PASS = "bd2024";
    
    /** Objeto que indica que la conexión activa. */
    protected Connection conexion;
    
    /**
     * Constructor que establece una conexión con la base de datos.
     * @throws SQLException si ocurre un error al conectarse.
     */
    public Conexion() throws SQLException {
        conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    
    /**
     * Método que cierra la conexión con la base de datos si está activa.
     * Captura y muestra cualquier SQLException que ocurra.
     */
    public void cerrar() {
        try {
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

	
	

