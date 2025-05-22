package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import bdd.DbAlquileres;
import bdd.DbVehiculo;
import modelo.Alquiler;
import modelo.Furgoneta;
import modelo.Moto;
import modelo.Turismo;
import modelo.Vehiculo;

/**
 * Clase que permite generar y guardar archivos JSON con información de vehículos y alquileres.
 * 
 * Genera tres archivos: vehiculos.json, alquilados.json y disponibles.json.
 * 
 * @author Pyto_Grupo_D
 * @version 1.0
 */
public class GuardarJson {

    /**
     * Método principal que ejecuta la generación y guardado de los archivos JSON con datos de vehículos y alquileres.
     *
     * @param args Argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        // Código dividido en tres bloques: todos los vehículos, alquilados hoy, y disponibles hoy.
        // Cada bloque obtiene datos desde la base de datos, los convierte a JSON y los guarda en un archivo.
        
        // TODOS LOS VEHICULOS
        DbVehiculo dbVehiculo;
        try {
            dbVehiculo = new DbVehiculo();
            ArrayList<Vehiculo> lista = dbVehiculo.obtenerVehiculos(null, null, false);
            String jsonString = generarJsonDeVehiculos(lista);
            System.out.println(jsonString);
            try (FileWriter writer = new FileWriter(new File("vehiculos.json"))) {
                writer.write(jsonString);
                System.out.println("Archivo 'vehiculos.json' creado con éxito.");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // VEHICULOS CON SUS ALQUILERES A DIA DE HOY
        DbAlquileres dbAlquiler;
        try {
            dbAlquiler = new DbAlquileres();
            ArrayList<Alquiler> listaA = dbAlquiler.obtenerAlquileresHoy();
            String jsonString2 = generarJsonDeVehiculosAlquilados(listaA);
            System.out.println(jsonString2);
            try (FileWriter writer2 = new FileWriter(new File("alquilados.json"))) {
                writer2.write(jsonString2);
                System.out.println("Archivo 'alquilados.json' creado con éxito.");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // VEHICULOS DISPONIBLES HOY
        try {
            dbVehiculo = new DbVehiculo();
            LocalDate hoy = LocalDate.now();
            ArrayList<Vehiculo> lista = dbVehiculo.obtenerVehiculos(hoy, hoy, true);
            String jsonString = generarJsonDeVehiculos(lista);
            System.out.println(jsonString);
            try (FileWriter writer = new FileWriter(new File("disponibles.json"))) {
                writer.write(jsonString);
                System.out.println("Archivo 'disponibles.json' creado con éxito.");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que genera una representación en formato JSON de una lista de vehículos.
     *
     * @param listaVehiculos Lista de objetos {@link Vehiculo} a convertir en JSON
     * @return Cadena JSON que representa la lista de vehículos
     */
    public static String generarJsonDeVehiculos(ArrayList<Vehiculo> listaVehiculos) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < listaVehiculos.size(); i++) {
            Vehiculo v = listaVehiculos.get(i);
            json.append("  {\n");
            json.append("    \"matricula\": \"").append(v.getMatricula()).append("\",\n");
            json.append("    \"marca\": \"").append(v.getMarca()).append("\",\n");
            json.append("    \"modelo\": \"").append(v.getModelo()).append("\",\n");
            json.append("    \"precioHora\": ").append(v.getPrecioH()).append(",\n");
            json.append("    \"fechaMatriculacion\": \"").append(v.getF_matriculacion().toString()).append("\",\n");
            json.append("    \"color\": \"").append(v.getColor()).append("\",\n");
            json.append("    \"plazas\": ").append(v.getPlazas()).append(",\n");

            if (v instanceof Turismo) {
                json.append("    \"tipo\": \"Turismo\",\n");
            }
            if (v instanceof Furgoneta) {
                json.append("    \"tipo\": \"Furgoneta\",\n");
            }
            if (v instanceof Moto) {
                json.append("    \"tipo\": \"Moto\",\n");
            }

            if (v instanceof Turismo) {
                Turismo t = (Turismo) v;
                json.append("    \"tipoTurismo\": \"").append(t.getTipo()).append("\",\n");
            } else {
                json.append("    \"tipoTurismo\": null,\n");
            }

            if (v instanceof Furgoneta) {
                Furgoneta f = (Furgoneta) v;
                json.append("    \"tipoFurgo\": \"").append(f.getTipo()).append("\",\n");
            } else {
                json.append("    \"tipoFurgo\": null,\n");
            }

            json.append("    \"proxMantenimiento\": \"").append(v.getProximo_mantenimiento().toString()).append("\"\n");
            json.append("  }");

            if (i != listaVehiculos.size() - 1) {
                json.append(",\n");
            } else {
                json.append("\n");
            }
        }

        json.append("]");
        return json.toString();
    }

    /**
     * Método que genera una representación en formato JSON de una lista de alquileres con la información
     * de los vehículos alquilados y sus respectivos clientes.
     *
     * @param listaAlquileres Lista de objetos {@link Alquiler} a convertir en JSON
     * @return Cadena JSON que representa los vehículos alquilados y sus datos asociados
     */
    public static String generarJsonDeVehiculosAlquilados(ArrayList<Alquiler> listaAlquileres) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < listaAlquileres.size(); i++) {
            Alquiler a = listaAlquileres.get(i);
            json.append("  {\n");
            json.append("    \"matricula\": \"").append(a.getVehiculo().getMatricula()).append("\",\n");
            json.append("    \"marca\": \"").append(a.getVehiculo().getMarca()).append("\",\n");
            json.append("    \"modelo\": \"").append(a.getVehiculo().getModelo()).append("\",\n");
            json.append("    \"precioHora\": ").append(a.getVehiculo().getPrecioH()).append(",\n");
            json.append("    \"fechaMatriculacion\": \"").append(a.getVehiculo().getF_matriculacion().toString()).append("\",\n");
            json.append("    \"color\": \"").append(a.getVehiculo().getColor()).append("\",\n");
            json.append("    \"plazas\": ").append(a.getVehiculo().getPlazas()).append(",\n");

            if (a.getVehiculo() instanceof Turismo) {
                json.append("    \"tipo\": \"Turismo\",\n");
            } else if (a.getVehiculo() instanceof Furgoneta) {
                json.append("    \"tipo\": \"Furgoneta\",\n");
            } else if (a.getVehiculo() instanceof Moto) {
                json.append("    \"tipo\": \"Moto\",\n");
            }

            if (a.getVehiculo() instanceof Turismo) {
                Turismo t = (Turismo) a.getVehiculo();
                json.append("    \"tipoTurismo\": \"").append(t.getTipo()).append("\",\n");
            } else {
                json.append("    \"tipoTurismo\": null,\n");
            }

            if (a.getVehiculo() instanceof Furgoneta) {
                Furgoneta f = (Furgoneta) a.getVehiculo();
                json.append("    \"tipoFurgo\": \"").append(f.getTipo()).append("\",\n");
            } else {
                json.append("    \"tipoFurgo\": null,\n");
            }

            json.append("    \"proxMantenimiento\": \"").append(a.getVehiculo().getProximo_mantenimiento().toString()).append("\",\n");
            json.append("    \"dni\": \"").append(a.getCliente().getDNI()).append("\",\n");
            json.append("    \"nombre\": \"").append(a.getCliente().getNombre()).append("\",\n");
            json.append("    \"telefono\": \"").append(a.getCliente().getTfno()).append("\",\n");
            json.append("    \"correo\": \"").append(a.getCliente().getCorreo()).append("\"\n");

            json.append("  }");

            if (i != listaAlquileres.size() - 1) {
                json.append(",\n");
            } else {
                json.append("\n");
            }
        }

        json.append("]");
        return json.toString();
    }
}
