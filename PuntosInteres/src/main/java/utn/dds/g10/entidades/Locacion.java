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
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Locacion")
@Access(value = AccessType.FIELD)
public class Locacion
 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locacionID")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "barrio")
	private String barrio;
	@Column(name = "callePrincipal")
	private String callePrincipal;
	@Column(name = "codigoPostal")
	private int codigoPostal;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
