package utn.dds.g10.gestores.Buscador;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import utn.dds.g10.entidades.POI;
@Entity
public class ResultadoBusquedaParcial implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RESULTADOPARCIAL_ID")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "RESULTADOPARCIALUSUARIO_ID", nullable = false)
	private ResultadoBusquedaParcialUsuario resultado;
	
	public ResultadoBusquedaParcialUsuario getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoBusquedaParcialUsuario resultado) {
		this.resultado = resultado;
	}

	@Column
	private String criterioBusqueda;
	@Column
	private int cantidadResultados;
	@Column
	private LocalDateTime fecha;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "resultado_poi", joinColumns = {
			@JoinColumn(name = "RESULTADOPARCIAL_ID", nullable = true) },
			inverseJoinColumns = { @JoinColumn(name = "id",
					nullable = true) })
	private List<POI> pois;
	
	public List<POI> getListaPOISbusquedaParcial() {
		return pois;
	}
	public void setListaPOISbusquedaParcial(List<POI> pois) {
		this.pois = pois;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}
	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}
	public int getCantidadResultados() {
		return cantidadResultados;
	}
	public void setCantidadResultados(int cantidadResultados) {
		this.cantidadResultados = cantidadResultados;
	}
	
}
