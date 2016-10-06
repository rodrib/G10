package utn.dds.g10.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.g10.model.ProcesoPoi;



public class ProcesoBajaPois  extends ProcesoPoi {
	
//	En el constructor es donde queda definido el proceso siguiente
	public ProcesoBajaPois(){
		setSiguienteProceso(ProcesoAgregarAcciones.class);
	}
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Obteniendo Pois a dar de baja");
		System.out.println("Dando de baja Pois");

	}

}
