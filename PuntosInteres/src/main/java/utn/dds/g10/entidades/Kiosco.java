package utn.dds.g10.entidades;
import java.time.LocalTime;
import java.time.DayOfWeek;




public class Kiosco implements RubroLocal {
	
	LocalTime HorarioInicio1 = LocalTime.of(10,0,0);
	LocalTime HorarioFin1 = LocalTime.of(13,0,0);
	LocalTime HorarioInicio2 = LocalTime.of(17,0,0);
	LocalTime HorarioFin2 = LocalTime.of(20,30,0);
	
	
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return 200;
	}
	
	public boolean EsDiaDisponible(LocalDateTime fecha){
		return fecha.getDayOfWeek() != DayOfWeek.SUNDAY;
	}
	public boolean EsHorarioDisponible(LocalDateTime fecha){
		Localtime HoraPoi = Localtime.of(fecha.getHour(),fecha.getMinutes(),0);
		return (HoraPoi.compareTo(HorarioInicio1)&&!HoraPoi.compareTo(HorarioFin1))||(HoraPoi.compareTo(HorarioInicio2)&&!HoraPoi.compareTo(HorarioFin2));
	}
}
