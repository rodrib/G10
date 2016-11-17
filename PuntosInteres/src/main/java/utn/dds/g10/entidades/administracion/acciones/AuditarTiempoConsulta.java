package utn.dds.g10.entidades.administracion.acciones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import utn.dds.g10.Utiles.Configuraciones;
import utn.dds.g10.Utiles.GestorMail;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AuditarTiempoConsulta extends Accion {
	
	int segundos;

	public AuditarTiempoConsulta(int seg) {
		this.segundos = seg;
		this.setaccion(this.getClass().getSimpleName());
	}

	public AuditarTiempoConsulta() {
		// TODO Auto-generated constructor stub
		this.setaccion(this.getClass().getSimpleName());
	}

	public void Ejecutar() {

		int timeout = Configuraciones.obtenerCantidadSegundosTimeOut();	
		
		if(this.segundos > timeout)
		{
			GestorMail.enviarMail(Configuraciones.obtenerMailAdministrador(), "Tiempo excedido", "La consulta realizada superó el tiempo máximo establecido");
		}
	}
}
