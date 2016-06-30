package utn.dds.g10.entidades;

import java.time.LocalDateTime;

public class LocalComercial extends TipoPoi {
	RubroLocal rubro;
	//List<DayOfWeek> diasDisponible;	

	@Override
	public float getDistanciaMaxima() {
		return rubro.getDistanciaMaxima();
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		return utn.dds.g10.Utiles.Util.CalcularDistancia(miLocacion, otraLocacion) < this.getDistanciaMaxima();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x){
		return EsDiaDisponible(fecha) && EsHorarioDisponible(fecha);
	}
	
	
	private boolean EsDiaDisponible(LocalDateTime fecha)
	{
		return rubro.EsDiaDisponible(fecha);
	}
	
	private boolean EsHorarioDisponible(LocalDateTime fecha)
	{
		return rubro.EsHorarioDisponible(fecha);
	}
	
	public RubroLocal getRubro() {
		return rubro;
	}

	public void setRubro(RubroLocal rubro) {
		this.rubro = rubro;
	}
}

