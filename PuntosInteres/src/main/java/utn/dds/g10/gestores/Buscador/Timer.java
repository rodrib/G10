package utn.dds.g10.gestores.Buscador;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

import utn.dds.g10.entidades.ResultadoConsulta;


public class Timer implements Buscador {
	
	Buscador buscadorPoi;
	ContadorSegundos contador;
	
	public ContadorSegundos getContador() {
		return contador;
	}

	public void setContador(ContadorSegundos contador) {
		this.contador = contador;
	}

	public Timer()
	{
		contador = new ContadorSegundos();
		buscadorPoi = new BuscadorPoi();
	}

	public ResultadoConsulta BuscarPoi(String criterioBusqueda) throws MalformedURLException, JSONException, IOException {
		return buscadorPoi.BuscarPoi(criterioBusqueda);
	}
	
	

}
