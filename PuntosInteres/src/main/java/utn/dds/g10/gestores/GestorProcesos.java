package utn.dds.g10.gestores;

import java.util.ArrayList;
import java.util.List;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import utn.dds.g10.procesos.Proceso;
import utn.dds.g10.procesos.ActualizadorLocales;
import utn.dds.g10.procesos.BajaPois;

public class GestorProcesos {

	List<Proceso> procesos;
	
	public List<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public  void Ejecutar() throws Exception {
		
		this.procesos = new ArrayList<Proceso>();
		
		
		this.procesos.add(new BajaPois());
		this.procesos.add(new ActualizadorLocales());
		
		for (Proceso proceso : procesos) {
			
			JobDetail jobDetail = proceso.obtenerJobDetail();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("TriggerActualizacionLocales", "grupo1")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule())
					.build();

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			
			scheduler.start();
			scheduler.scheduleJob(jobDetail, trigger);	
		}
	}
}