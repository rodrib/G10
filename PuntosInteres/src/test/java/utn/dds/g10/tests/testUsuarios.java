package utn.dds.g10.tests;
import org.junit.Assert;
import org.junit.Test;

import utn.dds.g10.datos.UsuariosDatos;
import utn.dds.g10.entidades.administracion.Usuario;


public class testUsuarios {
	
	@Test
	public void testObtenerUsuarioAdmin()   {
		
		UsuariosDatos datos = new UsuariosDatos();
		Usuario usuario = datos.ObtenerUsuario("admin", "admin");
		
		Assert.assertTrue("El usuario no existe", usuario != null);		
	}
	
	@Test
	public void testObtenerUsuarioTerminal()   {
		
		UsuariosDatos datos = new UsuariosDatos();
		Usuario usuario = datos.ObtenerUsuario("terminal", "terminal");
		
		Assert.assertTrue("El usuario no existe", usuario != null);		
	}

}
