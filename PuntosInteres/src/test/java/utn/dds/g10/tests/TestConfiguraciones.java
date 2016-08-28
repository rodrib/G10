package utn.dds.g10.tests;
import org.junit.Assert;
import org.junit.Test;

import utn.dds.g10.Utiles.Configuraciones;


public class TestConfiguraciones {


	@Test
	public void testGetMail()   {
		
		String miMail = Configuraciones.obtenerMailAdministrador();
		String mailAdmin = "guille86598@gmail.com";
		Assert.assertEquals(miMail, mailAdmin);
		
	}
}
