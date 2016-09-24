package utn.dds.g10.tests;

import org.junit.Assert;
import org.junit.Test;

import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.GestorProcesos;

public class ProcesosTest {
	
	@Test
	public void testearProcesoUno() throws Exception {
		
		GestorProcesos gestorP = new GestorProcesos();
		
		gestorP.Ejecutar();
		
		//Assert.assertTrue("Encontro el CGP por nombre", resultado.getPuntos().get(0).getNombre().equals("Comuna 3"));
		}

}
