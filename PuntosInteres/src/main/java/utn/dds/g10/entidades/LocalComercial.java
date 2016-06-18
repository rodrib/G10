package utn.dds.g10.entidades;

import java.time.LocalDateTime;

public class LocalComercial extends TipoPoi {
	RubroLocal rubro;
	//List<DayOfWeek> diasDisponible;

	@Override
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return rubro.getDistanciaMaxima();
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		// TODO Auto-generated method stub
		return utn.dds.g10.Utiles.Util.CalcularDistancia(miLocacion, otraLocacion) < this.getDistanciaMaxima();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha) {
		// TODO Auto-generated method stub
		return false;
	}
}

