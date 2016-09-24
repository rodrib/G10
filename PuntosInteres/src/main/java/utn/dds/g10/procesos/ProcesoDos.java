package utn.dds.g10.procesos;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ProcesoDos implements Job, Proceso {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Chau");
	}

	public JobDetail obtenerJobDetail() {
		return JobBuilder.newJob(ProcesoDos.class).withIdentity("JobActulizacionLocalesDos", "grupo1").build();
	}

}
