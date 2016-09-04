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
		((Timer)timer).getContador().Contar();
		timer.BuscarPoi(criterioBusqueda);
		((Timer)timer).getContador().Detener();
		
		
		//Aca se llama al guardar resultado de búsqueda con el tiempo que demoró en realizarla.
		//((Timer)timer).getContador().getSegundos();
		
		// TODO Auto-generated method stub
		return null;
	}

}
