package utn.dds.g10.model;

import java.util.ArrayList;
import java.util.List;

public class Cambios {
	private List<ElementoCambio> listaPoiCambiados = new ArrayList<ElementoCambio>();
	private String procesoEjecutado;

	public List<ElementoCambio> getListaPoi() {
		return listaPoiCambiados;
	}
	public void setListaPoi(List<ElementoCambio> listaPoi) {
		this.listaPoiCambiados = listaPoi;
	}
	public String getProcesoEjecutado() {
		return procesoEjecutado;
	}
	public void setProcesoEjecutado(String procesoEjecutado) {
		this.procesoEjecutado = procesoEjecutado;
	}

}
