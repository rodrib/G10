package utn.dds.g10.entidades;

import javax.persistence.*;

@Entity
@Table(name = "PRUEBA")
@Access(value=AccessType.FIELD)
public class PruebaPersistencia {

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

	@Column(name = "nombre")
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}