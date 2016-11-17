package utn.dds.g10.entidades;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.RolTerminal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
public class Locacion implements java.io.Serializable
 {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
//	@OneToOne
//    @JoinColumn(name = "locacion")
	 @OneToOne(mappedBy = "locacion")
	private POI poi;
	
	public POI getPoi() {
		return poi;
	}

	public void setPoi(POI poi) {
		this.poi = poi;
	}

	@Column(name = "barrio")
	private String barrio;
	@Column(name = "callePrincipal")
	private String callePrincipal;
	@Column(name = "codigoPostal")
	private int codigoPostal;
	
	@OneToOne
    @JoinColumn(name = "id_coordenada")
	private Coordenada coordenada;
	@Column(name = "departamento")
	private char departamento;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "entreCalles")
	private String entreCalles;
	@Column(name = "numero")
	private int numero;
	@Column(name = "pais")
	private String pais;
	@Column(name = "piso")
	private int piso;
	@Column(name = "provincia")
	private String provincia;
	@Column(name = "codigoComuna")
	private int codigoComuna;

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	//@OneToOne(mappedBy="locacion",fetch = FetchType.EAGER)
	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public char getDepartamento() {
		return departamento;
	}

	public void setDepartamento(char departamento) {
		this.departamento = departamento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEntreCalles() {
		return entreCalles;
	}

	public void setEntreCalles(String entreCalles) {
		this.entreCalles = entreCalles;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getCodigoComuna() {
		return codigoComuna;
	}

	public void setCodigoComuna(int codigoComuna) {
		this.codigoComuna = codigoComuna;
	}

}
