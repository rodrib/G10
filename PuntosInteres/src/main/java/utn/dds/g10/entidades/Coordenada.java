package utn.dds.g10.entidades;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Coordenada")
@Access(value = AccessType.FIELD)
public class Coordenada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coordenadaID")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "latitud")
	private float latitud;
	@Column(name = "longitud")
	private float longitud;

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
