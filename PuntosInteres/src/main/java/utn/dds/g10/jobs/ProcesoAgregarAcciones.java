package utn.dds.g10.jobs;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.g10.datos.UsuariosDatos;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.model.CambiosAccion;
import utn.dds.g10.model.ElementoCambioAccion;
import utn.dds.g10.model.HistorialCambios;
import utn.dds.g10.model.ProcesoPoi;

public class ProcesoAgregarAcciones  extends ProcesoPoi {
	
		public List<Accion> getAccionesAdicionales() {
		return accionesAdicionales;
	}

	public void setAccionesAdicionales(List<Accion> accionesAdicionales) {
		this.accionesAdicionales = accionesAdicionales;
	}

	List<Accion> accionesAdicionales = null;
	
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
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		accionesAdicionales = (ArrayList<Accion>) context.getJobDetail().getJobDataMap().get("listaAcciones");
		this.usuario = (Usuario) context.getJobDetail().getJobDataMap().get("usuario");
		
		CambiosAccion cambios = new CambiosAccion();
		cambios.setProcesoEjecutado(context.getJobDetail().getKey().getName());
		cambios.setUsuario(this.usuario);
		ArrayList<Accion> lista = (ArrayList<Accion>) this.usuario.getRol().getAcciones();
		List<Accion> copiaLista = (ArrayList<Accion>)  lista.clone();
		for (Accion accion : accionesAdicionales) {
			for (Accion accionUsuario : copiaLista) {
				
				ElementoCambioAccion elem = new ElementoCambioAccion();
				if(accionUsuario.getClass() == accion.getClass())
				{
					
					//Actualizo la accion.
					this.usuario.getRol().getAcciones().remove(accionUsuario);
					this.usuario.getRol().getAcciones().add(accion);
					elem.setAccionAnterior(accionUsuario);
					elem.setAccionActual(accion);
					elem.setCambio("modificacion");
					
				}
				else
				{
					//Si no existe la agrega.
					this.usuario.getRol().getAcciones().add(accion);
					elem.setAccionActual(accion);
					elem.setCambio("alta");
					
				}
				cambios.agregarListaAccion(elem);
			}
		}
		
		HistorialCambios.agregarCambiosAccion(cambios);
		
		
//		int x = 1 / 0; // Borrar comentario para forzar una excepci�n

	}

}
