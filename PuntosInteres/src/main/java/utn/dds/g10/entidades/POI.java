package utn.dds.g10.entidades;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import utn.dds.g10.DAO.DaoRelacional;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "poi")
public class POI implements java.io.Serializable{
	
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

	
	 //@OneToOne(mappedBy = "poi")
	 @OneToOne
    @JoinColumn(name = "id_locacion")
 	private Locacion locacion;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo")
	private TipoPoi tipo;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="texto", joinColumns=@JoinColumn(name="id"))
	private List<String> palabrasClaves = new ArrayList<String>();
	
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
//	@ElementCollection
//	@CollectionTable(name="palabrasClave", joinColumns=@JoinColumn(name="PoiID"))
//	@Column(name="palabrasClave")
	public List<String> getPalabrasClaves() {
		return palabrasClaves;
	}
	
	public void setPalabrasClaves(List<String> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}
	
	//@OneToOne(mappedBy="poi",fetch = FetchType.EAGER)
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
