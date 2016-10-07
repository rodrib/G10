package utn.dds.g10.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobListener;

import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.model.Cambios;
import utn.dds.g10.model.ElementoCambio;
import utn.dds.g10.model.HistorialCambios;
import utn.dds.g10.model.ProcesoListener;



public class ProcesoBajaPoisListener extends ProcesoListener implements JobListener {

	@Override
	protected void rollback(JobExecutionContext context) {
		System.out.println("Rollback de BajaPois");
		Cambios cambios = HistorialCambios.encontrarCambios(context.getJobDetail().getKey().getName());
		for (ElementoCambio elem : cambios.getListaPoi()){
			POI poi = elem.getPoi();
			Repositorio.AgregarPOI(poi);
		}
	}

}
