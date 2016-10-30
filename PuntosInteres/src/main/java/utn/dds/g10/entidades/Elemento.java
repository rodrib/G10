package utn.dds.g10.entidades;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Elemento implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "elemID")
	private int id;

	public int getId() {
		return id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id", nullable = false)
	private PruebaPersistencia prueba;
	
	public PruebaPersistencia getPrueba() {
		return prueba;
	}
	public void setPrueba(PruebaPersistencia prueba) {
		this.prueba = prueba;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column
	private int numero;
	@Column
	private String nombre;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
