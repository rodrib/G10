package utn.dds.g10.gestores.Buscador;

import java.time.LocalDate;

public class ResultadoBusquedaFecha {
	private LocalDate fechaHora;
	private int cantidadBusquedas;
	public LocalDate getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDate fechaHora) {
		this.fechaHora = fechaHora;
	}
	public int getCantidadBusquedas() {
		return cantidadBusquedas;
	}
	public void setCantidadBusquedas(int cantidadBusquedas) {
		this.cantidadBusquedas = cantidadBusquedas;
	}

}
