package utn.dds.g10.procesos;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidades.administracion.acciones.Accion;

public class AsignarAccionesUsuarios implements Job, Proceso {
	
	public AsignarAccionesUsuarios(Usuario us, List<Accion> acciones)
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

	List<Accion> accionesAdicionales;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	Usuario usuario;
	
	public JobDetail obtenerJobDetail() {
		return JobBuilder.newJob(AsignarAccionesUsuarios.class).withIdentity("JobActulizacionLocales", "grupo1").build();
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