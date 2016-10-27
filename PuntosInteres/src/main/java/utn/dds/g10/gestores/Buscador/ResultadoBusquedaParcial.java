package utn.dds.g10.gestores.Buscador;

import java.time.LocalDateTime;

public class ResultadoBusquedaParcial {

	private String criterioBusqueda;
	private int cantidadResultados;
	private LocalDateTime fecha;
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}
	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}
	public int getCantidadResultados() {
		return cantidadResultados;
	}
	public void setCantidadResultados(int cantidadResultados) {
		this.cantidadResultados = cantidadResultados;
	}
	
}
