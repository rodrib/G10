package utn.dds.g10.entidades;

import javax.persistence.*;

@Entity
public class PruebaPersistencia {

	@Id
	@GeneratedValue
	@Column(name = "id")
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nombre")
	String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public PruebaPersistencia(){};

}
