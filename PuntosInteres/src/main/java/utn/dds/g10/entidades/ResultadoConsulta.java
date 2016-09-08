package utn.dds.g10.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResultadoConsulta {
	private LocalDate fechaHora;
	//tiempoConsulta en segundos
	private int tiempoConsulta;
	private String criterioBusqueda;
	private int cantidadResultados;
	private List<POI> puntos = new ArrayList<POI>();
	private String usuario;

	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public int getTiempoConsulta() {
		return tiempoConsulta;
	}

	public void setTiempoConsulta(int tiempoConsulta) {
		this.tiempoConsulta = tiempoConsulta;
	}

	public int getCantidadResultados() {
		return cantidadResultados;
	}

	public void setCantidadResultados() {
		this.cantidadResultados = puntos.size();
	}
	
	public LocalDate getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDate fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<POI> getPuntos() {
		return puntos;
	}

	public void setPuntos(List<POI> puntos) {
		this.puntos = puntos;
	}

}
