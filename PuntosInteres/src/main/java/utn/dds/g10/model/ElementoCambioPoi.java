package utn.dds.g10.model;

import utn.dds.g10.entidades.POI;

public class ElementoCambioPoi {

	private POI poi = new POI();
	private String cambio;
	public POI getPoi() {
		return poi;
	}
	public void setPoi(POI poi) {
		this.poi = poi;
	}
	public String getCambio() {
		return cambio;
	}
	public void setCambio(String cambio) {
		this.cambio = cambio;
	}
	
	
}
