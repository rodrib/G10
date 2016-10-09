package utn.dds.g10.tests.e4;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
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

import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.entidades.administracion.acciones.AuditarTiempoConsulta;
import utn.dds.g10.jobs.ProcesoAgregarAcciones;
import utn.dds.g10.model.ProcesoListener;
import utn.dds.g10.model.ProcesoPoi;

public class AgregarAccionesTest {

	private void InicializarTest() {
		
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}
	
	@Test
	public void testearAgregarAcciones() throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, JSONException, IOException {
		
		Usuario usuarioTest = new Usuario();
		Rol rol = new RolAdministrador();
		usuarioTest.setRol(rol);
		usuarioTest.setNombre("admin");
		
		int cantAccionesUsuarioAntes =  usuarioTest.getRol().getAcciones().size();
		
		System.out.println("Acciones antes de ejecutar el proceso:" + cantAccionesUsuarioAntes);
		
		List<Accion> acciones = new ArrayList<Accion>();
		acciones.add(new AuditarTiempoConsulta(10));
		
		// Crea una instancia del planificador
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// Inicia el planificador
		scheduler.start();
		
		// Identificador del job
		JobKey key = new JobKey(ProcesoAgregarAcciones.class.getSimpleName());

		// Crea una instancia del proceso y con la opci�n requestRecovery(true) se fuerzan reintentos en caso de fallas
		JobDetail job = JobBuilder.newJob(ProcesoAgregarAcciones.class).withIdentity(key).requestRecovery(true)
				.build();
		
		job.getJobDataMap().put("listaAcciones", acciones);
		job.getJobDataMap().put("usuario", usuarioTest);

		// Crea una instancia del disparador (trigger) de procesos
 		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").startNow().build();

		// Crea una instancia del proceso inicial y su listener
		ProcesoPoi procesoInicial = new ProcesoAgregarAcciones();
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
		
		int cantidadAccionesUsuarioDespues = usuarioTest.getRol().getAcciones().size();
		
		System.out.println("Acciones despues de ejecutar el proceso:" + cantidadAccionesUsuarioDespues);
		
		Assert.assertTrue("Se agrego una accion",  cantAccionesUsuarioAntes < cantidadAccionesUsuarioDespues);
	}
	
}