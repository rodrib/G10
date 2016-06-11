package clases;

public abstract class TipoPoi {
	
	public float distanciaDefecto = 500;

	public abstract float getDistanciaMaxima();
	public abstract boolean estaCerca(Locacion miLocacion, Locacion otraLocacion);

}
