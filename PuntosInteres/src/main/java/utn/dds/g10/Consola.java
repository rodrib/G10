package utn.dds.g10;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import utn.dds.g10.jobs.ProcesoActualizaLocales;
import utn.dds.g10.model.ProcesoListener;
import utn.dds.g10.model.ProcesoPoi;

public class Consola {
	
	public static void main(String[] args) throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		ejecutaEjemploProcesosAnidadosConRollback();

	}

	private static void ejecutaEjemploProcesosAnidadosConRollback() throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// Crea una instancia del planificador
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// Inicia el planificador
		scheduler.start();
		
		// Identificador del job
		JobKey key = new JobKey(ProcesoActualizaLocales.class.getSimpleName());

		// Crea una instancia del proceso y con la opci�n requestRecovery(true) se fuerzan reintentos en caso de fallas
		JobDetail job = JobBuilder.newJob(ProcesoActualizaLocales.class).withIdentity(key).requestRecovery(true)
				.build();

		// Crea una instancia del disparador (trigger) de procesos
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").startNow().build();

		// Crea una instancia del proceso inicial y su listener
		ProcesoPoi procesoInicial = new ProcesoActualizaLocales();
		ProcesoListener procesoInicialListener = procesoInicial.getProcesoListener();
		
		// Asocia el listener al planificador
		scheduler.getListenerManager().addJobListener((JobListener)procesoInicialListener,
				KeyMatcher.keyEquals(key));

		// Agrega el proceso al planificador junto con su disparador (trigger)
		StdSchedulerFactory.getDefaultScheduler().scheduleJob(job, trigger);

		// Para darle tiempo al planificador que se puedea inicializar y
		// ejecutar los procesos
		Thread.sleep(1000);

		// Finaliza el planificador
		scheduler.shutdown();
	}

}
