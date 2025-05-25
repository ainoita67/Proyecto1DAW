package pruebas;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bdd.DbVehiculo;
import modelo.Moto;
import modelo.Vehiculo;


class TestVehiculos {

	@Test
	void testCrearVehiculo() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		DbVehiculo conn;
		try {
			conn = new DbVehiculo();
			assertTrue(conn.crearVehiculo(moto));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testVer1Vehiculo() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		DbVehiculo conn;
		try {
			conn = new DbVehiculo();
			assertEquals(moto, conn.ver1Vehiculo("1234qwe"));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testVeerTososClientes() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		DbVehiculo conn;
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>();
		lista.add(moto);
		try {
			conn = new DbVehiculo();
			assertArrayEquals(lista, conn.obtenerVehiculos(null,null,false));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEditarVehiculo() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		DbVehiculo conn;
		try {
			conn = new DbVehiculo();
			assertTrue(conn.actualizarVehiculo(moto));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEliminarUsuario() {
		DbVehiculo conn;
		try {
			conn = new DbVehiculo();
			assertTrue(conn.eliminarVehiculo("1234qwe"));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
