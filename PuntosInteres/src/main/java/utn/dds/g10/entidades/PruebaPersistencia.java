package utn.dds.g10.entidades;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name = "PRUEBA")
@Access(value=AccessType.FIELD)
public class PruebaPersistencia implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	ArrayList<Elemento> listaElem = new ArrayList<Elemento>();
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "PRUEBA")
	public ArrayList<Elemento> getLista() {
		return listaElem;
	}

	@Column(name = "nombre")
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}