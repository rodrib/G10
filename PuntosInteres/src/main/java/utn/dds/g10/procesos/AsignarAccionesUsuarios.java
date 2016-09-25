package utn.dds.g10.procesos;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AsignarAccionesUsuarios implements Job, Proceso {

	public JobDetail obtenerJobDetail() {
		return JobBuilder.newJob(AsignarAccionesUsuarios.class).withIdentity("JobActulizacionLocales", "grupo1").build();
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("¿Todo bien?");
		
	}

}
