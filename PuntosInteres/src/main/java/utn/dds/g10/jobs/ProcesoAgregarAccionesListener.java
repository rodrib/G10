package utn.dds.g10.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobListener;

import utn.dds.g10.datos.UsuariosDatos;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.model.CambiosAccion;
import utn.dds.g10.model.ElementoCambioAccion;
import utn.dds.g10.model.HistorialCambios;
import utn.dds.g10.model.ProcesoListener;



public class ProcesoAgregarAccionesListener extends ProcesoListener implements JobListener {

	@Override
	protected void rollback(JobExecutionContext context) {
		System.out.println("Rollback de AgregarAcciones");
		CambiosAccion cambios = HistorialCambios.encontrarCambiosAccion(context.getJobDetail().getKey().getName());
		Usuario usuario = cambios.getUsuario();
		Usuario usuarioProceso = (Usuario) context.getJobDetail().getJobDataMap().get("usuario");
		for (ElementoCambioAccion elem : cambios.getListaAccion()){
			
			if (elem.getCambio().equalsIgnoreCase("modificacion")){
				Accion accionActual = elem.getAccionActual();
				Accion accionAnterior = elem.getAccionAnterior();
				usuario.getRol().getAcciones().remove(accionActual);
				usuario.getRol().getAcciones().add(accionAnterior);
				
			}	
			else if (elem.getCambio().equalsIgnoreCase("alta")){
				Accion accionActual = elem.getAccionActual();
				usuario.getRol().getAcciones().remove(accionActual);

			}
		}
		usuarioProceso = usuario;	
	}

}
