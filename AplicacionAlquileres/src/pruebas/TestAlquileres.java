package pruebas;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bdd.DbAlquileres;
import bdd.DbUsuario;
import modelo.Alquiler;
import modelo.Cliente;
import modelo.Moto;
import modelo.Usuario;
import modelo.Vehiculo;


class TestAlquileres {

	@Test
	void testCrearAlquiler() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");

		Alquiler alquiler = new Alquiler(cliente, moto, LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-02"), 200.0);
		DbAlquileres conn;
		try {
			conn = new DbAlquileres();
			assertTrue(conn.crearAlquiler(alquiler));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testverAlquileres() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");

		Alquiler alquiler = new Alquiler(cliente, moto, LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-02"), 200.0);
		DbAlquileres conn;

		ArrayList<Alquiler> lista = new ArrayList<Alquiler>();
		lista.add(alquiler);
		try {
			conn = new DbAlquileres();
			assertArrayEquals(lista, conn.obtenerAlquileres());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testverAlquileresCliente() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");

		Alquiler alquiler = new Alquiler(cliente, moto, LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-02"), 200.0);
		DbAlquileres conn;

		ArrayList<Alquiler> lista = new ArrayList<Alquiler>();
		lista.add(alquiler);
		try {
			conn = new DbAlquileres();
			assertArrayEquals(lista, conn.obtenerAlquileresPorCliente("1234qwe"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testverAlquileresVehiculo() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");

		Alquiler alquiler = new Alquiler(cliente, moto, LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-02"), 200.0);
		DbAlquileres conn;

		ArrayList<Alquiler> lista = new ArrayList<Alquiler>();
		lista.add(alquiler);
		try {
			conn = new DbAlquileres();
			assertArrayEquals(lista, conn.obtenerAlquileresPorVehiculo("111111111m"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testverAlquileresHoy() {
		Vehiculo moto = new Moto("1234qwe", "marca", "modelo", 12.0, LocalDate.parse("2025-02-02"), LocalDate.parse("2025-02-02"), "azul", 2);
		Cliente cliente = new Cliente("111111111m", "Ainoa", "667020202", "ainoa@ainoa.com", "c mayor 10 alcañiz");

		Alquiler alquiler = new Alquiler(cliente, moto, LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-02"), 200.0);
		DbAlquileres conn;

		ArrayList<Alquiler> lista = new ArrayList<Alquiler>();
		lista.add(alquiler);
		try {
			conn = new DbAlquileres();
			assertArrayEquals(lista, conn.obtenerAlquileresHoy());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEliminarAlquiler() {
		DbAlquileres conn;
		try {
			conn = new DbAlquileres();
			assertTrue(conn.eliminarAlquiler("1234qwe", "111111111m", LocalDate.parse("2025-02-01")));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
