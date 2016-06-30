package utn.dds.g10.entidades;

import java.time.LocalDate;
import java.util.List;

public class ResultadoConsulta {
	LocalDate fechaHora;
	List<POI> puntos;

	public LocalDate getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDate fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<POI> getPuntos() {
		return puntos;
	}
	
	public static List<POI> buscarPOI (String tag){
		List<POI> listaPOIsSeleccionados = new ArrayList <POI>();
		for(int i=0;i<listaPOIs.size();i++) {
			if (listaPOIs.get(i).buscarTag(tag)){
				listaPOIsSeleccionados.add(listaPOIs.get(i));
			}
		}
		return listaPOIsSeleccionados;
	}
	
	List<String> POI = new ArrayList<String>();			
	list.add("Banco Frances");
	list.add("Banco ICBC");
	list.add("Banco HSBC");
	list.add("CGP 1");
	list.add("CGP 2");
	list.add("Kiosco MO");
	list.add("Kiosco Nep");
	list.add("Libreria AS");
	list.add("Libreria SI");

	public void setPuntos(List<POI> puntos) {
		this.puntos = puntos;
	}

}
