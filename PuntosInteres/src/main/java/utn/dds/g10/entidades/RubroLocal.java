package utn.dds.g10.entidades;

import java.time.LocalDateTime;

public interface  RubroLocal {

	public float getDistanciaMaxima();
	public boolean EsDiaDisponible(LocalDateTime fecha);
	public boolean EsHorarioDisponible(LocalDateTime fecha);
	public boolean CumpleCondicionBusqueda(String condicion);
}
