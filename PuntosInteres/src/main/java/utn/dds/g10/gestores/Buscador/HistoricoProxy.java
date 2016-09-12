package utn.dds.g10.gestores.Buscador;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

import utn.dds.g10.Utiles.Configuraciones;
import utn.dds.g10.Utiles.GestorMail;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.administracion.Usuario;


public class HistoricoProxy implements Buscador {
	
	private Timer timer;
	private Usuario usuarioBusqueda;
	
	public HistoricoProxy(Usuario usuario)
	{
		timer = new Timer();
		usuarioBusqueda = usuario;
	}

	public ResultadoConsulta BuscarPoi(String criterioBusqueda) throws MalformedURLException, JSONException, IOException {
		
		ResultadoConsulta resultado = new ResultadoConsulta();
		
		timer.getContador().Contar();
		resultado = timer.BuscarPoi(criterioBusqueda);
		timer.getContador().Detener();

		int segundos= timer.getContador().getSegundos();

		//Según el rol activa o desactiva la auditoria.
		this.usuarioBusqueda.getRol().AuditarTiempoConsulta(segundos);
		
		//Según el rol activa o desactiva la auditoria del resultado de la búsqueda. 
		this.usuarioBusqueda.getRol().AuditarResultadoConsulta(resultado, segundos, criterioBusqueda);
		
		return resultado;
	}

}
