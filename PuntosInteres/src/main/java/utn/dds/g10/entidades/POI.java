package utn.dds.g10.entidades;

public class POI {
	
	Locacion locacion;
	String nombre;
	TipoPoi tipo;
	public Locacion getLocacion() {
		return locacion;
	}
	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoPoi getTipo() {
		return tipo;
	}
	public void setTipo(TipoPoi tipo) {
		this.tipo = tipo;
	}
	
	private ArrayList<String> tags;
	
	public boolean buscarTag(String tag){
		Boolean coincide = false;
		if (this.getNombre().contains(tag))
			coincide = true;
		if (tags != null &&  tags.contains(tag))
			coincide = true;

		
		return coincide;		
	}

	
}
