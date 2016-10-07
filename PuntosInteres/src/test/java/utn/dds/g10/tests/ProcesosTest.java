package utn.dds.g10.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.entidades.administracion.acciones.AuditarResultadoConsulta;
import utn.dds.g10.entidades.administracion.acciones.AuditarTiempoConsulta;
import utn.dds.g10.gestores.GestorProcesos;
import utn.dds.g10.procesos.AsignarAccionesUsuarios;
import utn.dds.g10.procesos.Proceso;

import java.util.ArrayList;

public class ProcesosTest {

	@Test
	public void testearProcesoUno() throws Exception {

		GestorProcesos gestorP = new GestorProcesos();

		gestorP.Ejecutar();

		// Assert.assertTrue("Encontro el CGP por nombre",
		// resultado.getPuntos().get(0).getNombre().equals("Comuna 3"));
	}

	@Test
	public void testearProcesoAcciones() throws Exception {
		Usuario usuarioTest = new Usuario();
		
		usuarioTest.setRol(new RolAdministrador());
		
		int cantAccionesUsuarioAntes =  usuarioTest.getRol().getAcciones().size();
		
		System.out.println("acciones antes de ejecutar el proceso:" + cantAccionesUsuarioAntes);
		
		List<Accion> acciones = new ArrayList<Accion>();
		acciones.add(new AuditarTiempoConsulta(10));

		GestorProcesos gestor = new GestorProcesos();
		List<Proceso> misPorcesosAEjecutar = new ArrayList<Proceso>();

		misPorcesosAEjecutar.add(new AsignarAccionesUsuarios(usuarioTest, acciones));
		gestor.setProcesos(misPorcesosAEjecutar);
		gestor.Ejecutar();
	
		int cantidadAccionesUsuarioDespues = usuarioTest.getRol().getAcciones().size();
		
		System.out.println("acciones despues de ejecutar el proceso:" + cantidadAccionesUsuarioDespues);
		
		Assert.assertTrue("No se agregaron acciones.",  cantAccionesUsuarioAntes < cantidadAccionesUsuarioDespues);
	}
}
