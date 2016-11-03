package utn.dds.g10.entidades;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ServicioCGP implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "servicioCGPID")
	private int id;
	@Column
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CGP", nullable = false)
	private CGP cgp;
	
	
	public CGP getCgp() {
		return cgp;
	}
	public void setCgp(CGP cgp) {
		this.cgp = cgp;
	}
	
	
	private ArrayList<Horarios> horarios = new ArrayList<Horarios>();
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idTipoPoi")
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
			if(horario != null){
			if(horario.getDiaSemana()==fecha.getDayOfWeek().getValue()){
				
				LocalTime HoraPoi = LocalTime.of(fecha.getHour(),fecha.getMinute(),0);
				LocalTime HoraDesde = LocalTime.of(horario.getHoraDesde(),horario.getMinutosDesde(),0);
				LocalTime HoraHasta = LocalTime.of(horario.getHoraHasta(),horario.getMinutosHasta(),0);
				
				if ( ((HoraPoi.compareTo(HoraDesde)==1) && (HoraPoi.compareTo(HoraHasta)==-1))){
					return true;
				}	
				
			}
		}
		
		}
		return false;
	}
	
}
