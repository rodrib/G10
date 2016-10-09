package utn.dds.g10.tests.e4;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
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

import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.Buscador.BuscadorSinReportes;
import utn.dds.g10.jobs.ProcesoBajaPois;
import utn.dds.g10.model.ProcesoListener;
import utn.dds.g10.model.ProcesoPoi;


public class BajaPoisTest {
	
	@Test
	public void testearProcesoBajaPois() throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, JSONException, IOException {
		// Crea una instancia del planificador
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// Inicia el planificador
		scheduler.start();
		
		// Identificador del job
		JobKey key = new JobKey(ProcesoBajaPois.class.getSimpleName());

		// Crea una instancia del proceso y con la opci�n requestRecovery(true) se fuerzan reintentos en caso de fallas
		JobDetail job = JobBuilder.newJob(ProcesoBajaPois.class).withIdentity(key).requestRecovery(true)
				.build();

		// Crea una instancia del disparador (trigger) de procesos
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").startNow().build();

		// Crea una instancia del proceso inicial y su listener
		ProcesoPoi procesoInicial = new ProcesoBajaPois();
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
		
		BuscadorSinReportes buscador = new BuscadorSinReportes();
		ResultadoConsulta resultado = buscador.buscarSinReportes("Banco ICBC");
		assertTrue("No existe el Banco ICBC", resultado.getPuntos().size() == 0);
		
		resultado = buscador.buscarSinReportes("CGP Comuna 3");
		assertTrue("No existe el CGP Comuna 3", resultado.getPuntos().size() == 0);
		
		resultado = buscador.buscarSinReportes("Kiosco SI");
		assertTrue("No existe el Kiosco SI", resultado.getPuntos().size() == 0);
	}
	
}
