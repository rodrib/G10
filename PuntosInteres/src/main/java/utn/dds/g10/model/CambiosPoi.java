package utn.dds.g10.model;

import java.util.ArrayList;
import java.util.List;

public class CambiosPoi {
	private List<ElementoCambioPoi> listaPoiCambiados = new ArrayList<ElementoCambioPoi>();
	private String procesoEjecutado;

	public List<ElementoCambioPoi> getListaPoi() {
		return listaPoiCambiados;
	}
	public void agregarListaPoi(ElementoCambioPoi elem) {
		this.listaPoiCambiados.add(elem);
	}
	public void setListaPoi(List<ElementoCambioPoi> listaPoi) {
		this.listaPoiCambiados = listaPoi;
	}
	public String getProcesoEjecutado() {
		return procesoEjecutado;
	}
	public void setProcesoEjecutado(String procesoEjecutado) {
		this.procesoEjecutado = procesoEjecutado;
	}

}
