package utn.dds.g10.entidades.administracion;

import java.util.List;

import utn.dds.g10.entidades.administracion.acciones.Accion;

public class Usuario {
	
	private Rol rol;
	private String nombre;
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
}
