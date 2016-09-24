package utn.dds.g10.procesos;

import org.quartz.*;


public class ProcesoUno implements Job, Proceso {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Hola");
	}

	public JobDetail obtenerJobDetail() {
		return JobBuilder.newJob(ProcesoUno.class).withIdentity("JobActulizacionLocales", "grupo1").build();
	}
	
}

