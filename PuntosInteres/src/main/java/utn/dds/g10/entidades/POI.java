package utn.dds.g10.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "POI")
@Access(value = AccessType.FIELD)
public class POI {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PoiID")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Locacion locacion;
	@Column(name = "nombre")
	private String nombre;
	@Transient
	private TipoPoi tipo;
	@Transient
	private ArrayList<String> palabrasClaves = new ArrayList<String>();
	@Column(name = "estadoAlta")
	private boolean estadoAlta;
	
	public POI(){
		estadoAlta=true;
	}
	
	public boolean getEstadoAlta() {
		return estadoAlta;
	}
	public void setEstadoAlta(boolean estadoAlta) {
		this.estadoAlta = estadoAlta;
	}
	public ArrayList<String> getPalabrasClaves() {
		return palabrasClaves;
	}
	public void setPalabrasClaves(ArrayList<String> palabrasClaves) {
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
