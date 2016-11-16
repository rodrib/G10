package utn.dds.g10.tests;
 
 import org.junit.Assert;
 import org.junit.Test;
 
 import utn.dds.g10.Utiles.GestorMail;
 
 public class TestMail {
 
 	@Test
 	public void testEnviarMail()  {
 		
 		String respuesta;
 		respuesta = GestorMail.enviarMail("bautista.rod@gmail.com", "prueba", "Hola mundo");
 		
 		Assert.assertTrue(respuesta=="Mail enviado correctamente");
 	}
 }
	
