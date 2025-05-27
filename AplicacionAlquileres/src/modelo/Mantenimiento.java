package modelo;

import java.time.LocalDate;

public class Mantenimiento {
	public Mantenimiento(Vehiculo vehiculo, String descripcion, LocalDate fecha) {
		super();
		this.vehiculo = vehiculo;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}
	private Vehiculo vehiculo;
	private String descripcion;
	private LocalDate fecha;
	
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
