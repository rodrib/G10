package utn.dds.g10.entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Rubro_Libreria")
public class Libreria extends RubroLocal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	private LocalTime HorarioInicio1 = LocalTime.of(10, 0, 0);
	@Column
	private LocalTime HorarioFin1 = LocalTime.of(13, 0, 0);
	@Column
	private LocalTime HorarioInicio2 = LocalTime.of(17, 0, 0);
	@Column
	private LocalTime HorarioFin2 = LocalTime.of(20, 30, 0);

	public float getDistanciaMaxima() {
		return 500;
	}

	public boolean EsDiaDisponible(LocalDateTime fecha) {
		return fecha.getDayOfWeek() != DayOfWeek.SUNDAY;
	}

	public boolean EsHorarioDisponible(LocalDateTime fecha) {
		LocalTime HoraPoi = LocalTime.of(fecha.getHour(), fecha.getMinute(), 0);
		if (((HoraPoi.compareTo(HorarioInicio1) == 1) && (HoraPoi
				.compareTo(HorarioFin1) == -1))
				|| ((HoraPoi.compareTo(HorarioInicio2) == 1) && (HoraPoi
						.compareTo(HorarioFin2) == -1))) {
			return true;
		}

		return false;
	}

	public boolean CumpleCondicionBusqueda(String condicion) {
		// El nobre de la clase es el nombre del rubro.
		return (this.getClass().getName().contains(condicion));
	}

	@Override
	public String tipoRubro() {
		return "Libreria";
	}

}
