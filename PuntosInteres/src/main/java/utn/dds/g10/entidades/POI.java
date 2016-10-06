package utn.dds.g10.entidades;

import java.util.ArrayList;
import java.util.List;

public class POI {
	
	private Locacion locacion;
	private String nombre;
	private TipoPoi tipo;
	private List<String> palabrasClaves = new ArrayList<String>();
	
	public List<String> getPalabrasClaves() {
		return palabrasClaves;
	}
	public void setPalabrasClaves(List<String> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}
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
}
