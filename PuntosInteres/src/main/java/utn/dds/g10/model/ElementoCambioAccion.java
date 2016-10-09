package utn.dds.g10.model;

import utn.dds.g10.entidades.administracion.acciones.Accion;

public class ElementoCambioAccion {

	private Accion accionActual;
	private Accion accionAnterior;
	public Accion getAccionActual() {
		return accionActual;
	}
	public void setAccionActual(Accion accionActual) {
		this.accionActual = accionActual;
	}
	public Accion getAccionAnterior() {
		return accionAnterior;
	}
	public void setAccionAnterior(Accion accionAnterior) {
		this.accionAnterior = accionAnterior;
	}
	private String cambio;

	public String getCambio() {
		return cambio;
	}
	public void setCambio(String cambio) {
		this.cambio = cambio;
	}
	
}
