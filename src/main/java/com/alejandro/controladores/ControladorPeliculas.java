package com.alejandro.controladores;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ControladorPeliculas {
	
	private static HashMap<String, String> listaPeliculas = new HashMap<String, String>();
	
	public ControladorPeliculas() {
		listaPeliculas.put("Winnie the Pooh", "Don Hall");	
		listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
		listaPeliculas.put("Tarzán", "Kevin Lima");		
		listaPeliculas.put("Mulán", "Barry Cook");
		listaPeliculas.put("Oliver", "Kevin Lima");	
		listaPeliculas.put("Big Hero 6", "Don Hall");
	}
	
	@GetMapping("/peliculas")
	public String obtenerTodasLasPeliculas() {
		 return listaPeliculas.keySet().toString();
	}
	
	 @GetMapping("peliculas/{nombre}")
	  public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
	      if (listaPeliculas.containsKey(nombre)) {
	          return nombre + " fue dirigida por " + listaPeliculas.get(nombre);
	      } 
	      else {
	          return "La película no se encuentra en nuestra lista.";
	      }
	  }
	 
	 @GetMapping("/peliculas/director/{nombre}")
	  public String obtenerPeliculasPorDirector(@PathVariable String nombre) {
	      String peliculas = listaPeliculas.entrySet().stream()
	              .filter(entry -> entry.getValue().equalsIgnoreCase(nombre))
	              .map(Map.Entry :: getKey) 
	              .collect(Collectors.joining());
	      
	      if (peliculas.isEmpty()) {
	          return "No contamos con películas con ese director en nuestra lista.";
	      }
	      else {
	          return "Películas dirigidas por " + nombre + ": " + peliculas;
	      }
	  }
	 
	 
	 
}
