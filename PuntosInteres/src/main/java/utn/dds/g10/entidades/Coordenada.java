package utn.dds.g10.entidades;

public class Coordenada {

	private float latitud;
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
