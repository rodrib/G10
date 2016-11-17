package utn.dds.g10.entidades;

import java.util.ArrayList;


import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import utn.dds.g10.DAO.DaoRelacional;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "POI")
public class POI implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PoiID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public POI obtenerPOI(int id) {
		ArrayList<POI> lista = new ArrayList<POI>();
		lista = (ArrayList<POI>) DaoRelacional.obtenerPois();
		for (POI p : lista) {
			if (p.getId()==id)
				return p;
		}
		return null;
	}
	
	private Locacion locacion;
	@Column(name = "nombre")
	private String nombre;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoPoi tipo;

	private ArrayList<String> palabrasClaves = new ArrayList<String>();
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
	@ElementCollection
	@CollectionTable(name="palabrasClaves", joinColumns=@JoinColumn(name="PoiID"))
	public ArrayList<String> getPalabrasClaves() {
		return palabrasClaves;
	}
	public void setPalabrasClaves(ArrayList<String> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "POI", cascade = CascadeType.ALL)
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
