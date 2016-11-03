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
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class  RubroLocal implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private	int idRubro;

	public int getIdRubro() {
		return idRubro;
	}
	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}
	@Column
	public abstract float getDistanciaMaxima();
	public abstract boolean EsDiaDisponible(LocalDateTime fecha);
	public abstract boolean EsHorarioDisponible(LocalDateTime fecha);
	public abstract boolean CumpleCondicionBusqueda(String condicion);
}
