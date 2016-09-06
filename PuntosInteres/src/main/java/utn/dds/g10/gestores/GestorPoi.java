package utn.dds.g10.gestores;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import org.json.JSONException;

import utn.dds.g10.entidades.*;
import utn.dds.g10.gestores.Buscador.HistoricoProxy;

public class GestorPoi {

	private HistorialConsultas historial;
	private HistoricoProxy historicoProxy;
	
	public GestorPoi(){
		historicoProxy = new HistoricoProxy();
		historial = new HistorialConsultas();
	}


	public ResultadoConsulta BuscarPoi(String criterioBusqueda, String usuario) throws MalformedURLException, JSONException, IOException {
		
		// Retorna el resultado de una consulta.
		ResultadoConsulta resultado = new ResultadoConsulta();

		
		resultado = historicoProxy.BuscarPoi(criterioBusqueda);
		resultado.setUsuario(usuario);
		// Guardo la consulta en el Historial
		 historial.AgregarResultado(resultado);

		return resultado;
	}

	public float CalcularDistanciaEntrePuntos(POI puntoUno, POI puntoDos) {
		return utn.dds.g10.Utiles.Util.CalcularDistancia(
				puntoUno.getLocacion(), puntoDos.getLocacion());
	}

	public boolean EstaCerca(POI poiUno, POI poiDos) {
		return this.EstaCerca(poiUno, poiDos.getLocacion());
	}

	public boolean EstaCerca(POI poiUno, Locacion miLocacion) {
		return poiUno.getTipo().estaCerca(poiUno.getLocacion(), miLocacion);
	}

	public boolean EstaDisponible(POI poi, LocalDateTime fecha, String x) {
		return poi.getTipo().estaDisponible(fecha, x);
	}
}
	
	

