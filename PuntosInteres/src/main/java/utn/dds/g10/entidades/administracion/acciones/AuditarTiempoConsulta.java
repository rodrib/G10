package utn.dds.g10.entidades.administracion.acciones;

import utn.dds.g10.Utiles.Configuraciones;
import utn.dds.g10.Utiles.GestorMail;

public class AuditarTiempoConsulta implements Accion {
	
	int segundos;

	public AuditarTiempoConsulta(int seg) {
		this.segundos = seg;
	}

	public AuditarTiempoConsulta() {
		// TODO Auto-generated constructor stub
	}

	public void Ejecutar() {

		int timeout = Configuraciones.obtenerCantidadSegundosTimeOut();	
		
		if(this.segundos > timeout)
		{
			GestorMail.enviarMail(Configuraciones.obtenerMailAdministrador(), "Tiempo excedido", "La consulta realizada superó el tiempo máximo establecido");
		}
	}
}
