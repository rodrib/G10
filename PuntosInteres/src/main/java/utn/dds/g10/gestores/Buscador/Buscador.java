package utn.dds.g10.gestores.Buscador;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

import utn.dds.g10.entidades.ResultadoConsulta;

public interface Buscador {
	
	public ResultadoConsulta BuscarPoi(String criterioBusqueda) throws MalformedURLException, JSONException, IOException;
		
}
