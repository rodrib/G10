package utn.dds.g10.entidades.administracion;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import utn.dds.g10.entidades.administracion.acciones.Accion;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "nombre")
@JsonSubTypes({
@Type(value = RolAdministrador.class),
@Type(value = RolTerminal.class)
})
@Entity
@Table(name="ROL")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Rol implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private	int idRol;
	
	@Column
	private String nombre;
	
	@Transient
	private List<utn.dds.g10.entidades.Menu> menues;
	
	//public abstract List<utn.dds.g10.entidades.Menu> getListadoMenues();


	public abstract List<utn.dds.g10.entidades.Menu> getMenues();

	public void setMenues(List<utn.dds.g10.entidades.Menu> menues) {
		this.menues = menues;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	
	@Transient
	List<Accion> acciones;
	
	public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}
	
	public abstract void setAcciones();

	public void ejecutarAccion(Accion accionAEjecutar)
	{
		for (Accion accion : this.acciones) {
			if(accion.getClass() == accionAEjecutar.getClass())
			{
				accionAEjecutar.Ejecutar();
			}
		}
	}

}
	