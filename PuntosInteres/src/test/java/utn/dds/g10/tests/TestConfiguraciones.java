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
	

	@Test
	public void testGetURlbancos()   {
		String urlBancosConfig = Configuraciones.obtenerUrlBancos();
		String urlBancos = "http://localhost:8081/Consultas/banco";
		Assert.assertEquals(urlBancosConfig, urlBancos);
	}
	
	@Test
	public void testGetTimeOutCincoSegundos()   {
		Assert.assertEquals(Configuraciones.obtenerCantidadSegundosTimeOut(), 5);	
	}
}
