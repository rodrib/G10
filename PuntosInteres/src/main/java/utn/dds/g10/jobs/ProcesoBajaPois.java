package utn.dds.g10.jobs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.mappers.PoiJSON;
import utn.dds.g10.model.Cambios;
import utn.dds.g10.model.ElementoCambio;
import utn.dds.g10.model.HistorialCambios;
import utn.dds.g10.model.ProcesoPoi;



public class ProcesoBajaPois  extends ProcesoPoi {
	
//	En el constructor es donde queda definido el proceso siguiente
	public ProcesoBajaPois(){
		setSiguienteProceso(ProcesoAgregarAcciones.class);
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Obteniendo Pois a dar de baja");
		List<POI> listaPois = null;
		
		try {
			Cambios cambios = new Cambios();
			cambios.setProcesoEjecutado(context.getJobDetail().getKey().getName());
			listaPois = PoiJSON.obtenerPois("BajaPois.txt");
			
			for (POI poi : listaPois){
				ElementoCambio elem = new ElementoCambio();
				elem.setPoi(poi);
				elem.setCambio("baja");
				cambios.getListaPoi().add(elem);
			}
			HistorialCambios.agregarCambios(cambios);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Dando de baja Pois");
		
		for (POI poi : listaPois) {
			
			Repositorio.EliminarPOI(poi);
		}
		
	}

}
