package utn.dds.g10.entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Libreria implements RubroLocal {
	
	LocalTime HorarioInicio1 = LocalTime.of(10,0,0);
	LocalTime HorarioFin1 = LocalTime.of(13,0,0);
	LocalTime HorarioInicio2 = LocalTime.of(17,0,0);
	LocalTime HorarioFin2 = LocalTime.of(20,30,0);
	
	public float getDistanciaMaxima() {
		return 500;
	}

	public boolean EsDiaDisponible(LocalDateTime fecha) {
		return fecha.getDayOfWeek() != DayOfWeek.SUNDAY;
	}

	public boolean EsHorarioDisponible(LocalDateTime fecha){
		LocalTime HoraPoi = LocalTime.of(fecha.getHour(),fecha.getMinute(),0);
		if ( ((HoraPoi.compareTo(HorarioInicio1)==1) && (HoraPoi.compareTo(HorarioFin1)==-1)) || ( (HoraPoi.compareTo(HorarioInicio2)==1) && (HoraPoi.compareTo(HorarioFin2)==-1)) ){
			return true;
		}
		
		return false;
	}

}
