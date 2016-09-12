package utn.dds.g10.entidades.administracion;

import utn.dds.g10.Utiles.Configuraciones;
import utn.dds.g10.Utiles.GestorMail;
import utn.dds.g10.entidades.ResultadoConsulta;

public class RolAdministrador implements Rol {

	public void AuditarTiempoConsulta(int segundos) {
		int timeout = Configuraciones.obtenerCantidadSegundosTimeOut();
		
		if(segundos > timeout)
		{
			//Mandar mail admin.
			GestorMail.enviarMail(Configuraciones.obtenerMailAdministrador(), "Tiempo excedido", "La consulta realizada superó el tiempo máximo establecido");
		}
	}

	public void AuditarResultadoConsulta(ResultadoConsulta resultado,
			int demoraConsulta, String Consulta) {
		// TODO Auto-generated method stub
		
	}
	
}
