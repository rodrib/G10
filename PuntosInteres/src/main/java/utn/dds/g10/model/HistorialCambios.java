package utn.dds.g10.model;

import java.util.ArrayList;
import java.util.List;

public class HistorialCambios {

	private static List<Cambios> listadoHistorialCambios = new ArrayList<Cambios>();

	public static List<Cambios> getListadoHistorialCambios() {
		return listadoHistorialCambios;
	}

	public static void setListadoHistorialCambios(
			List<Cambios> listadoHistorialCambios) {
		HistorialCambios.listadoHistorialCambios = listadoHistorialCambios;
	}
	
	public static void agregarCambios(Cambios cambios){
		listadoHistorialCambios.add(cambios);
	}
	
	public static void modificarCambios(Cambios cambios){
		int indice=0;
		for (Cambios cambiosElem : listadoHistorialCambios){
			if (cambiosElem.getProcesoEjecutado().equalsIgnoreCase(cambios.getProcesoEjecutado())){
				break;
			}
			indice++;
		}
		listadoHistorialCambios.add(indice, cambios);
	}
	
	public static Cambios encontrarCambios(String procesoEjecutado){
		Cambios cambios = new Cambios();
		for (Cambios cambiosElem : listadoHistorialCambios){
			if (cambiosElem.getProcesoEjecutado().equalsIgnoreCase(procesoEjecutado)){
				cambios = cambiosElem;
				break;
			}
		}
		return cambios;
	}
}
