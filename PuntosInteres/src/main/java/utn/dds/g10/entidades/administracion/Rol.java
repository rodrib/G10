package utn.dds.g10.entidades.administracion;

import java.util.List;

import utn.dds.g10.entidades.administracion.acciones.Accion;

public abstract class Rol {
	
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
