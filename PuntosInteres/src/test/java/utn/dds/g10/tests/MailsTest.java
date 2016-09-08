package utn.dds.g10.tests;

import org.junit.Assert;
import org.junit.Test;

import utn.dds.g10.Utiles.GestorMail;

public class MailsTest {

	@Test
	public void testEnviarMail()  {
		
	
		GestorMail.enviarMail("bautista.rod@gmail.com", "prueba", "Hola mundo");
		
		Assert.assertTrue(1 == 1);
	}
}
