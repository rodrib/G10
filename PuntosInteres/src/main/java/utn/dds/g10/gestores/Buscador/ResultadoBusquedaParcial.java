package utn.dds.g10.gestores.Buscador;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import utn.dds.g10.entidades.POI;
@Entity
public class ResultadoBusquedaParcial implements Serializable {

	
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
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "resultados_id", nullable = true)
	private ResultadoBusquedaParcialUsuario resultado;
	
	@Column
	private String criterioBusqueda;
	@Column
	private int cantidadResultados;
	@Column
	private LocalDateTime fecha;
	
	@OneToMany(mappedBy = "resultado")
	private List<POI> listaPOISbusquedaParcial;
	
	public List<POI> getListaPOISbusquedaParcial() {
		return listaPOISbusquedaParcial;
	}
	public void setListaPOISbusquedaParcial(List<POI> listaPOISbusquedaParcial) {
		this.listaPOISbusquedaParcial = listaPOISbusquedaParcial;
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
