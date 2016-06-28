
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class ServicioCGP {
	String nombre;
	LocalTime HorarioInicio1 = LocalTime.of(10,0,0);
	LocalTime HorarioFin1 = LocalTime.of(13,0,0);
	LocalTime HorarioInicio2 = LocalTime.of(17,0,0);
	LocalTime HorarioFin2 = LocalTime.of(20,30,0);
	public boolean estaDisponible(LocalDateTime fecha){
		return EsDiaDisponible(fecha)&&EsHorarioDisponible(fecha);
	}
	public boolean EsHorarioDisponible(LocalDateTime fecha){
		Localtime HoraPoi = Localtime.of(fecha.getHour(),fecha.getMinutes(),0);
		return (HoraPoi.compareTo(HorarioInicio1)&&!HoraPoi.compareTo(HorarioFin1))||(HoraPoi.compareTo(HorarioInicio2)&&!HoraPoi.compareTo(HorarioFin2));
	}
	
	public boolean EsDiaDisponible(LocalDateTime fecha){
		return fecha.getDayOfWeek() != DayOfWeek.SUNDAY;
	}
}
