package utn.dds.g10.entidades;

import java.time.LocalDateTime;

public class ParadaColectivo extends TipoPoi {

	@Override
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		// TODO Auto-generated method stub
		return utn.dds.g10.Utiles.Util.CalcularDistancia(miLocacion, otraLocacion) < getDistanciaMaxima();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x) {
		// TODO Auto-generated method stub
		return true;
	}

}
