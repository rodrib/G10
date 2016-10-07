package utn.dds.g10.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobListener;

import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.model.Cambios;
import utn.dds.g10.model.ElementoCambio;
import utn.dds.g10.model.HistorialCambios;
import utn.dds.g10.model.ProcesoListener;


public class ProcesoActualizaLocalesListener extends ProcesoListener implements JobListener {

	@Override
	protected void rollback(JobExecutionContext context) {
		System.out.println("Rollback de ActualizaLocales");
		Cambios cambios = HistorialCambios.encontrarCambios(context.getJobDetail().getKey().getName());
		for (ElementoCambio elem : cambios.getListaPoi()){
			POI poi = elem.getPoi();
			if (elem.getCambio().equalsIgnoreCase("modificacion"))
				Repositorio.ModificarPOI(poi);
			else if (elem.getCambio().equalsIgnoreCase("alta"))
				Repositorio.EliminarPOI(poi);
		}
	}


}
