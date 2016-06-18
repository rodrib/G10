package utn.dds.g10.entidades;

import java.time.LocalDateTime;

public abstract class TipoPoi {
	
	public float distanciaDefecto = 500;

	public abstract float getDistanciaMaxima();
	public abstract boolean estaCerca(Locacion miLocacion, Locacion otraLocacion);
	public abstract boolean estaDisponible(LocalDateTime fecha);

}
