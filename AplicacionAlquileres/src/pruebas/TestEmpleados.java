package pruebas;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bdd.DbUsuario;
import modelo.Usuario;


class TestEmpleados {

	@Test
	void testCrearUsuario() {
		Usuario usuario = new Usuario("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz", "constraseña", 2);
		DbUsuario conn;
		try {
			conn = new DbUsuario();
			assertTrue(conn.crearUsuario(usuario));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testVer1Cliente() {
		Usuario usuario = new Usuario("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz", "constraseña", 2);
		DbUsuario conn;
		try {
			conn = new DbUsuario();
			assertEquals(usuario, conn.ver1Usuario("11111111m"));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testVer1Cliente2() {
		Usuario usuario = new Usuario("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz", "constraseña", 2);
		DbUsuario conn;
		try {
			conn = new DbUsuario();
			assertEquals(usuario, conn.ver1Usuario("11111111m", 2));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testVeerTososClientes() {
		Usuario usuario = new Usuario("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz", "constraseña", 2);
		DbUsuario conn;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		lista.add(usuario);
		try {
			conn = new DbUsuario();
			assertArrayEquals(lista, conn.verTodosUsuarios(2));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEditarUsuario() {
		Usuario usuario = new Usuario("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz", "constraseña", 2);
		DbUsuario conn;
		try {
			conn = new DbUsuario();
			assertTrue(conn.actualizarUsuario(usuario));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEliminarUsuario() {
		DbUsuario conn;
		try {
			conn = new DbUsuario();
			assertTrue(conn.eliminarUsuario("11111111m", 2));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testCredenciales() {
		DbUsuario conn;
		try {
			conn = new DbUsuario();
			assertTrue(conn.verificarCredenciales("11111111m", "contraseña"));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
