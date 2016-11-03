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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "Locacion")
@Access(value = AccessType.FIELD)
public class Locacion implements java.io.Serializable
 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locacionID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private POI poi;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
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

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "Locacion", cascade = CascadeType.ALL)
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
