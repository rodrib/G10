package utn.dds.g10.entidades;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
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
		LocalTime HoraPoi = LocalTime.of(fecha.getHour(),fecha.getMinute(),0);
		if ( ((HoraPoi.compareTo(HorarioInicio1)==1) && (HoraPoi.compareTo(HorarioFin1)==-1)) || ( (HoraPoi.compareTo(HorarioInicio2)==1) && (HoraPoi.compareTo(HorarioFin2)==-1)) ){
			return true;
		}	
		return false;
	}
	
	public boolean EsDiaDisponible(LocalDateTime fecha){
		return fecha.getDayOfWeek()!= DayOfWeek.SUNDAY;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
