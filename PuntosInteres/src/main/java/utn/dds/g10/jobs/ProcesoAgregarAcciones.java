package utn.dds.g10.jobs;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.model.ProcesoPoi;



public class ProcesoAgregarAcciones  extends ProcesoPoi {
	
	public ProcesoAgregarAcciones(Usuario us, List<Accion> acciones)
	{
		this.setAccionesAdicionales(acciones);
		this.setUsuario(us);
	}

	public List<Accion> getAccionesAdicionales() {
		return accionesAdicionales;
	}

	public void setAccionesAdicionales(List<Accion> accionesAdicionales) {
		this.accionesAdicionales = accionesAdicionales;
	}

	List<Accion> accionesAdicionales = new ArrayList<Accion>();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	Usuario usuario;
	
//	En el constructor es donde queda definido el proceso siguiente
	public ProcesoAgregarAcciones(){
		setSiguienteProceso(null);
	}
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		for (Accion accion : accionesAdicionales) {
			for (Accion accionUsuario : this.usuario.getRol().getAcciones()) {
				if(accionUsuario.getClass() == accion.getClass())
				{
					//Actualizo la accion.
					this.usuario.getRol().getAcciones().remove(accionUsuario);
					this.usuario.getRol().getAcciones().add(accion);
				}
				else
				{
					//Si no existe la agrega.
					this.usuario.getRol().getAcciones().add(accion);
				}
			}
		}

	}

}
