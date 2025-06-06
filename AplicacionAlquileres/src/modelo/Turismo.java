// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package modelo;

import java.time.LocalDate;

/**
 * Clase que gestiona los datos de un turismo.
 * Hereda todos los atributos y comportamientos de la clase {@code Vehiculo}.
 * 
 * @author Pyto_Grupo_D
 * @version 1.0
 */
public class Turismo extends Vehiculo {

    /**
     * Tipo de turismo.
     */
    private String tipo;

    /**
     * Constructor que crea un nuevo turismo con los datos especificados.
     * 
     * @param matricula Matrícula del vehículo.
     * @param marca Marca del vehículo.
     * @param modelo Modelo del vehículo.
     * @param precioH Precio por hora del alquiler.
     * @param f_matriculacion Fecha de matriculación del vehículo.
     * @param proximo_mantenimiento Fecha del próximo mantenimiento programado.
     * @param color Color del vehículo.
     * @param plazas Número de plazas del vehículo.
     * @param tipo Tipo de turismo (sedán, compacto, SUV, etc.).
     */
    public Turismo(String matricula, String marca, String modelo, Double precioH, LocalDate f_matriculacion,
                   LocalDate proximo_mantenimiento, String color, int plazas, String tipo) {
        super(matricula, marca, modelo, precioH, f_matriculacion, proximo_mantenimiento, color, plazas);
        this.tipo = tipo;
    }

    /**
     * Método que devuelve el tipo de turismo.
     * 
     * @return Tipo de turismo (por ejemplo: sedán, SUV, etc.).
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que establece el tipo de turismo.
     * 
     * @param tipo Tipo de turismo a asignar.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
