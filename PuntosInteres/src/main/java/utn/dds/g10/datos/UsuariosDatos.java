package utn.dds.g10.datos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.RolTerminal;
import utn.dds.g10.entidades.administracion.Usuario;

public class UsuariosDatos {
	
	private List<Usuario> usuarios; 
	
	private enum RolEnum {ADMIN, TERMINAL};
	
	public UsuariosDatos()
	{
		usuarios = new ArrayList<Usuario>();
		ObtenerTodosUsuarios();
	}
	
	
	///La password se debería comprobar en la base de datos.
	public Usuario ObtenerUsuario(String username, String password) 
	{	
		try {
			
			for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				
				if(usuario.getNombre() == username)
				{
					return usuario;
				}
			}
			
			throw new Exception("El usuario no existe");
			
		} catch (Exception e) {
		
		}
		return null;
	}
	
	public List<Usuario> ObtenerTodosUsuarios()
	{
		
		RolAdministrador rolAdmin = new RolAdministrador();
		RolTerminal rolTerminal = new RolTerminal();
		
		Usuario administrador = new Usuario();
		administrador.setNombre("admin");
		administrador.setRol(rolAdmin);
		
		Usuario terminal = new Usuario();
		terminal.setNombre("terminal");
		terminal.setRol(rolTerminal);
		
		usuarios.add(administrador);
		usuarios.add(terminal);
		
		return usuarios;
	}
	
	public List<String> ObtenerModulosPorRol(RolEnum tipoRol)
	{
		List<String> modulos = new ArrayList<String>();
		
		switch (tipoRol) {
		case ADMIN:
			modulos.add("Consultas");
			modulos.add("Reportes");
			modulos.add("ABMPoi");
			break;
		case TERMINAL:
			modulos.add("Consultas");
		default:
			break;
		}
		
		return modulos;
	}
}
