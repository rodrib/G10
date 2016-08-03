package utn.dds.g10.entidades;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

public class ServicioCGP {
	
	String nombre;
	ArrayList<Horarios> horarios;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Horarios> getHorarios() {
		return horarios;
	}
	public void setHorarios(ArrayList<Horarios> horarios) {
		this.horarios = horarios;
	}

	public boolean estaDisponible(LocalDateTime fecha){

		Iterator<Horarios> i = horarios.iterator();
		while(i.hasNext()){
			Horarios horario = (Horarios) i.next();
			if(horario.getDiaSemana()==fecha.getDayOfWeek().getValue()){
		
				LocalTime HoraPoi = LocalTime.of(fecha.getHour(),fecha.getMinute(),0);
				LocalTime HoraDesde = LocalTime.of(horario.getHoraDesde(),horario.getMinutosDesde(),0);
				LocalTime HoraHasta = LocalTime.of(horario.getHoraHasta(),horario.getMinutosHasta(),0);
				
				if ( ((HoraPoi.compareTo(HoraDesde)==1) && (HoraPoi.compareTo(HoraHasta)==-1))){
					return true;
				}	
				
			}
		
		}
		return false;
	}
	
}
