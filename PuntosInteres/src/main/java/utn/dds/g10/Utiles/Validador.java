package utn.dds.g10.Utiles;

import utn.dds.g10.entidades.administracion.Usuario;

public class Validador {
	
	public static void ValidarPermisosUsuario(Usuario usuario, String modulo) throws Exception
	{
		
		try {
			
			   for (int i = 0; i < usuario.getRol().getModulos().size(); i++) {
				
				   if(modulo == (usuario.getRol().getModulos()).get(i))
				   {
					   return;
				   }
			} 

			   throw new Exception(); 
		} catch (Exception e) {
			throw new Exception("Error: no tiene permisos suficientes");
		}	
	}
}
