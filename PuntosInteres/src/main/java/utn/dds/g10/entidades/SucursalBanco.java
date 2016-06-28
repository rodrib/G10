package utn.dds.g10.entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class SucursalBanco extends TipoPoi {
	
	private static int HoraInicio = 10;
	private static int HoraFin = 15;

	@Override
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return super.distanciaDefecto;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha, String x) {
		// TODO Auto-generated method stub
		return EsDiaSemana(fecha) && EsHorarioDisponible(fecha);
	}
	
	
	private boolean EsDiaSemana(LocalDateTime fecha)
	{
		return fecha.getDayOfWeek() != DayOfWeek.SUNDAY && fecha.getDayOfWeek() != DayOfWeek.SATURDAY;
	}
	
	private boolean EsHorarioDisponible(LocalDateTime fecha)
	{
		return  fecha.getHour() > HoraInicio && fecha.getHour() < HoraFin;
	}

}
