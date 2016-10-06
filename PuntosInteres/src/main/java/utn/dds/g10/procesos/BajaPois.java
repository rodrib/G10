package utn.dds.g10.procesos;

import org.quartz.*;


public class BajaPois implements Job, Proceso {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Hola");
	}

	public JobDetail obtenerJobDetail() {
		return JobBuilder.newJob(BajaPois.class).withIdentity("JobBajaPois", "grupo1").build();
	}
	
}

