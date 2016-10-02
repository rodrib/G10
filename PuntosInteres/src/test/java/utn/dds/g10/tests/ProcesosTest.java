package utn.dds.g10.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.gestores.GestorProcesos;
import utn.dds.g10.procesos.AsignarAccionesUsuarios;
import utn.dds.g10.procesos.Proceso;

import java.util.ArrayList;

public class ProcesosTest {
	
	@Test
	public void testearProcesoUno() throws Exception {
		
		GestorProcesos gestorP = new GestorProcesos();
		
		gestorP.Ejecutar();
		
		//Assert.assertTrue("Encontro el CGP por nombre", resultado.getPuntos().get(0).getNombre().equals("Comuna 3"));
		}
	
	@Test
	public void testearProcesoAcciones() throws Exception {
	
	List<Accion> acciones = new ArrayList<Accion>();
	
	Proceso proceso = new AsignarAccionesUsuarios(new Usuario(), acciones);
			
			JobDetail jobDetail = proceso.obtenerJobDetail();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("TriggerActualizacionLocales", "grupo1")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule())
					.build();

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			
			scheduler.start();
			scheduler.scheduleJob(jobDetail, trigger);	
	

		//Assert.assertTrue("Encontro el CGP por nombre", resultado.getPuntos().get(0).getNombre().equals("Comuna 3"));
		}

}
