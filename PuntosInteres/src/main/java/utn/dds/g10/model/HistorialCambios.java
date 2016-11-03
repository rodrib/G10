package utn.dds.g10.model;

import java.util.ArrayList;
import java.util.List;

public class HistorialCambios {

	private static List<CambiosPoi> listadoHistorialCambiosPoi = new ArrayList<CambiosPoi>();
	private static List<CambiosAccion> listadoHistorialCambiosAcciones = new ArrayList<CambiosAccion>();

	public static List<CambiosPoi> getListadoHistorialCambiosPoi() {
		return listadoHistorialCambiosPoi;
	}

	public static void setListadoHistorialCambiosPoi(
			List<CambiosPoi> listadoHistorialCambios) {
		HistorialCambios.listadoHistorialCambiosPoi = listadoHistorialCambios;
	}
	
	public static void agregarCambiosPoi(CambiosPoi cambios){
		HistorialCambios.listadoHistorialCambiosPoi.add(cambios);
	}
	
	public static void modificarCambiosPoi(CambiosPoi cambios){
		int indice=0;
		for (CambiosPoi cambiosElem : HistorialCambios.listadoHistorialCambiosPoi){
			if (cambiosElem.getProcesoEjecutado().equalsIgnoreCase(cambios.getProcesoEjecutado())){
				for (ElementoCambioPoi elem: cambiosElem.getListaPoi()){
					cambios.agregarListaPoi(elem);
				}
				break;
			}
			indice++;
		}
		HistorialCambios.listadoHistorialCambiosPoi.add(indice, cambios);
	}
	
	public static CambiosPoi encontrarCambiosPoi(String procesoEjecutado){
		CambiosPoi cambios = new CambiosPoi();
		for (CambiosPoi cambiosElem : HistorialCambios.listadoHistorialCambiosPoi){
			if (cambiosElem.getProcesoEjecutado().equalsIgnoreCase(procesoEjecutado)){
				cambios = cambiosElem;
				break;
			}
		}
		return cambios;
	}
	
	public static void agregarCambiosAccion(CambiosAccion cambios){
		HistorialCambios.listadoHistorialCambiosAcciones.add(cambios);
	}
	
	public static void modificarCambiosAccion(CambiosAccion cambios){
		int indice=0;
		for (CambiosAccion cambiosElem : HistorialCambios.listadoHistorialCambiosAcciones){
			if (cambiosElem.getProcesoEjecutado().equalsIgnoreCase(cambios.getProcesoEjecutado())){
				break;
			}
			indice++;
		}
		HistorialCambios.listadoHistorialCambiosAcciones.add(indice, cambios);
	}
	
	public static CambiosAccion encontrarCambiosAccion(String procesoEjecutado){
		CambiosAccion cambios = new CambiosAccion();
		for (CambiosAccion cambiosElem : HistorialCambios.listadoHistorialCambiosAcciones){
			if (cambiosElem.getProcesoEjecutado().equalsIgnoreCase(procesoEjecutado)){
				cambios = cambiosElem;
				break;
			}
		}
		return cambios;
	}
}
