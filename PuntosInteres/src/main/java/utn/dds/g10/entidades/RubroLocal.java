package utn.dds.g10.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class  RubroLocal implements Serializable  {
	public abstract String tipoRubro();

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
	
	@OneToOne(mappedBy = "rubro")
	private LocalComercial local;
	
	public LocalComercial getLocal() {
		return local;
	}
	public void setLocal(LocalComercial local) {
		this.local = local;
	}
	@Column
	public abstract float getDistanciaMaxima();
	public abstract boolean EsDiaDisponible(LocalDateTime fecha);
	public abstract boolean EsHorarioDisponible(LocalDateTime fecha);
	public abstract boolean CumpleCondicionBusqueda(String condicion);
}