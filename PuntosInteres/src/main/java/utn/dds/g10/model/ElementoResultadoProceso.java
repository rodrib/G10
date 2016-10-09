package utn.dds.g10.model;

import java.util.Date;

public class ElementoResultadoProceso {

	private Date fechaHoraInicio;
	private Date fechaHoraFin;
	private String procesoEjecutado;
	private String resultado;
	private String mensajeError;
	private String usuario;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}
	public Date getFechaHoraFin() {
		return fechaHoraFin;
	}
	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
	public String getProcesoEjecutado() {
		return procesoEjecutado;
	}
	public void setProcesoEjecutado(String procesoEjecutado) {
		this.procesoEjecutado = procesoEjecutado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
}
