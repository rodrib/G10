package utn.dds.g10.entidades;

import java.time.LocalDate;
import java.util.List;

public class ResultadoConsulta {
	LocalDate fechaHora;
	List<POI> puntos;

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
