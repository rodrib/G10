package utn.dds.g10.entidades;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Access(value = AccessType.FIELD)
public class ResultadoConsulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rsId;
	public int getRsId() {
		return rsId;
	}

	public void setRsId(int rsId) {
		this.rsId = rsId;
	}

	@JsonIgnoreProperties()
	@Column(name = "fecha_hora")
	private LocalDate fechaHora;
	
	@Column
	private int tiempoConsulta;
	
	@Column
	private String criterioBusqueda;
	
	@Column
	private int cantidadResultados;
	
	@OneToMany(mappedBy = "poi")
	private List<POI> puntos = new ArrayList<POI>();
	
	@Transient
	private List<TestEntidad> array = new ArrayList<TestEntidad>();
	
	public List<TestEntidad> getArray() {
		return array;
	}

	public void setArray(List<TestEntidad> array) {
		this.array = array;
	}

	public void setCantidadResultados(int cantidadResultados) {
		this.cantidadResultados = cantidadResultados;
	}

	@Column
	private String usuario;

	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public int getTiempoConsulta() {
		return tiempoConsulta;
	}

	public void setTiempoConsulta(int tiempoConsulta) {
		this.tiempoConsulta = tiempoConsulta;
	}

	public int getCantidadResultados() {
		return cantidadResultados;
	}

	public void setCantidadResultados() {
		this.cantidadResultados = puntos.size();
	}
	
	public LocalDate getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDate fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<POI> getPuntos() {
		return puntos;
	}

	public void setPuntos(List<POI> puntos) {
		this.puntos = puntos;
	}

}
