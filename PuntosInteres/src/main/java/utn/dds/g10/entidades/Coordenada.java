package utn.dds.g10.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "coordenada")
public class Coordenada implements java.io.Serializable{

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
	@Column(name = "latitud")
	private float latitud;
	@Column(name = "longitud")
	private float longitud;
	
	@OneToOne(mappedBy = "coordenada")
	private Locacion locacion;

//	@OneToOne(fetch = FetchType.EAGER)
//	@PrimaryKeyJoinColumn
	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}
	
	public Coordenada(){};

	public Coordenada(float lat, float longi)
	{
		this.setLatitud(lat);
		this.setLongitud(longi);
	}
	
	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

}
