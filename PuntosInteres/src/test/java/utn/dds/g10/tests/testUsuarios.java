package utn.dds.g10.tests;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import utn.dds.g10.Utiles.Validador;
import utn.dds.g10.datos.UsuariosDatos;
import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.RolTerminal;
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
	
	@Test
	public void testUsuarioConPermisos() throws Exception   {
		
		Usuario usuarioConsulta = new Usuario();
		
		Rol rolAdmin = new RolAdministrador();
		
		rolAdmin.setModulos(new ArrayList<String>());
		rolAdmin.getModulos().add("Altapoi");
		
		usuarioConsulta.setRol(rolAdmin);
		
		Boolean tienePermiso = false;
		
		try {
			Validador.ValidarPermisosUsuario(usuarioConsulta, "Altapoi");
			tienePermiso  = true	;
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		Assert.assertTrue("Acceso incorrecto al módulo.", tienePermiso);		
	}
	
	@Test
	public void testUsuarioSinPermisos() throws Exception   {
		
		Usuario usuarioConsulta = new Usuario();
		
		Rol rolConsulta = new RolTerminal();
		
		rolConsulta.setModulos(new ArrayList<String>());
		
		usuarioConsulta.setRol(rolConsulta);
		
		Boolean tienePermiso = false;
		
		try {
			Validador.ValidarPermisosUsuario(usuarioConsulta, "Altapoi");
			tienePermiso  = true	;
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		Assert.assertTrue("Acceso correcto al módulo.", !tienePermiso);		
	}
	
}
