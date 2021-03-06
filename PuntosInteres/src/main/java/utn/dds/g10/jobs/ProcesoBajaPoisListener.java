package utn.dds.g10.jobs;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.quartz.JobExecutionContext;
import org.quartz.JobListener;

import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.model.CambiosPoi;
import utn.dds.g10.model.ElementoCambioPoi;
import utn.dds.g10.model.HistorialCambios;
import utn.dds.g10.model.ProcesoListener;



public class ProcesoBajaPoisListener extends ProcesoListener implements JobListener {

	@Override
	protected void rollback(JobExecutionContext context) throws MalformedURLException, JSONException, IOException {
		System.out.println("Rollback de BajaPois");
		CambiosPoi cambios = HistorialCambios.encontrarCambiosPoi(context.getJobDetail().getKey().getName());
		for (ElementoCambioPoi elem : cambios.getListaPoi()){
			POI poi = elem.getPoi();
			Repositorio.AgregarPOI(poi);
		}
	}

}
