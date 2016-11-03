package utn.dds.g10.entidades.administracion;

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
@Table(name = "Usuarios")
@Access(value = AccessType.FIELD)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usrID")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "usrNombre")
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Rol rol;

	public Rol getRol() {
		return rol;
	}

	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
