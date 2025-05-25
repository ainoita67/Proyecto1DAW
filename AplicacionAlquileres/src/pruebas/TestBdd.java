package pruebas;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bdd.DbCliente;
import modelo.Cliente;

class TestBdd {

	@Test
	void testCrearClientes() {
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");
		DbCliente conn;
		try {
			conn = new DbCliente();
			assertTrue(conn.crearCliente(cliente));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testVer1Cliente() {
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");
		DbCliente conn;
		try {
			conn = new DbCliente();
			assertEquals(cliente, conn.ver1Cliente("11111111m"));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testVeerTososClientes() {
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");
		DbCliente conn;
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		lista.add(cliente);
		try {
			conn = new DbCliente();
			assertArrayEquals(lista, conn.verTodosClientes());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEditarClientes() {
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");
		DbCliente conn;
		try {
			conn = new DbCliente();
			assertTrue(conn.actualizarCliente(cliente));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEliminarClientes() {
		DbCliente conn;
		try {
			conn = new DbCliente();
			assertTrue(conn.eliminarCliente("11111111m"));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
