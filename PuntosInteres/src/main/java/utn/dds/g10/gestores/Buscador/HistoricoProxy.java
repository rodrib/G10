package utn.dds.g10.gestores.Buscador;

import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONException;

import utn.dds.g10.entidades.ResultadoConsulta;


public class HistoricoProxy implements Buscador {
	
	Buscador timer;
	
	public HistoricoProxy()
	{
		timer = new Timer();
	}

	public ResultadoConsulta BuscarPoi(String criterioBusqueda) throws MalformedURLException, JSONException, IOException {
		
		//Tabla de cantidad de resultados por usuario (parcial)
		//Tabla por todos los usuarios (total)
		
		ResultadoConsulta resultado = new ResultadoConsulta();
		
		((Timer)timer).getContador().Contar();
		resultado = timer.BuscarPoi(criterioBusqueda);
		((Timer)timer).getContador().Detener();
		
		//Aca se llama al guardar resultado de búsqueda con el tiempo que demoró en realizarla.
		resultado.setTiempoConsulta(((Timer)timer).getContador().getSegundos());
		resultado.setCriterioBusqueda(criterioBusqueda);
		resultado.setCantidadResultados();
		
		return resultado;
	}

}
