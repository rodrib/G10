package utn.dds.g10.entidades.administracion;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import utn.dds.g10.entidades.administracion.acciones.Accion;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Rol implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private	int idRol;
	
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
