package utn.dds.g10.tests.e4;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.junit.Before;
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
import utn.dds.g10.jobs.ProcesoActualizaLocales;
import utn.dds.g10.model.ElementoResultadoProceso;
import utn.dds.g10.model.ProcesoListener;
import utn.dds.g10.model.ProcesoPoi;
import utn.dds.g10.model.ResultadoProcesos;

public class ActualizadorLocalesTest {



	private void InicializarTest() {
		
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}
	
	@Test
	public void testearProcesoActualizadorLocales() throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, JSONException, IOException {
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
		
		BuscadorSinReportes buscador = new BuscadorSinReportes();
		ResultadoConsulta resultado = buscador.buscarSinReportes("Carrousel");
		assertTrue("Existe el Local Comercial Carrousel", resultado.getPuntos().get(0).getNombre().equals("Carrousel"));
		
		resultado = buscador.buscarSinReportes("Kiosco MO");
		assertTrue("El Local Kiosco MO tiene de palabras clave caramelos y chocolates", resultado.getPuntos().get(0).getPalabrasClaves().get(0).equals("caramelos") && resultado.getPuntos().get(0).getPalabrasClaves().get(1).equals("chocolates"));
		
		assertTrue("El proceso finalizo correctamente", ((ElementoResultadoProceso) ResultadoProcesos.getListadoResultadoProcesos().get(0)).getResultado().equals("ok") );
	}
	
}

