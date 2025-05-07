package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Connection conn = null;
	    
 	private static final String DB_HOST = "192.168.4.140";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "alquiler";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "bd2024";
    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";
    
    protected Connection conexion;
    
    
    public Conexion() throws SQLException {
        conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}

	
	

