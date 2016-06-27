package utn.dds.g10.entidades;

public interface  RubroLocal {
	
	public float getDistanciaMaxima();
	public boolean EsDiaDisponible(LocalDateTime fecha);
	public boolean EsHorarioDisponible(LocalDateTime fecha);
}
