package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import bdd.DbVehiculo;
import modelo.Alquiler;
import modelo.Furgoneta;
import modelo.Moto;
import modelo.Turismo;
import modelo.Vehiculo;

public class GuardarJson {
	
	public static void main(String[] args) {
		DbVehiculo dbVehiculo;
		try {
			dbVehiculo = new DbVehiculo();
		 
	        ArrayList<Vehiculo> lista = dbVehiculo.obtenerVehiculos(null, null, false);
	
	        // Generar el JSON
	        String jsonString = generarJsonDeVehiculos(lista);
	        
	        System.out.println(jsonString);
	        
	        // Guardar en un archivo
	        try (FileWriter writer = new FileWriter(new File("vehiculos.json"))) {
	            writer.write(jsonString);
	            System.out.println("Archivo 'vehiculos.json' creado con éxito.");
	        }

        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
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
	        }
	        if (a.getVehiculo() instanceof Furgoneta) {
	        	json.append("    \"tipo\": \"Furgoneta\",\n");
	        }
	        if (a.getVehiculo() instanceof Moto) {
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
	        
	        json.append("    \"proxMantenimiento\": \"").append(a.getVehiculo().getProximo_mantenimiento().toString()).append("\"\n");
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
