package utn.dds.g10.entidades;

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
public abstract class TipoPoi implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private	Long idTipoPoi;
	
	public Long getIdTipoPoi() {
		return idTipoPoi;
	}

	public void setIdTipoPoi(Long idTipoPoi) {
		this.idTipoPoi = idTipoPoi;
	}
	@Column
	public float distanciaDefecto = 500;
	
	public abstract float getDistanciaMaxima();
	public abstract boolean estaCerca(Locacion miLocacion, Locacion otraLocacion);
	public abstract boolean estaDisponible(LocalDateTime fecha, String x);
	public abstract boolean CumpleCondicionBusqueda(String condicion);
	public abstract String tipoPOI();
	public abstract TipoPoi obtenerPOI(int id);
}
