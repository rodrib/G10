package utn.dds.g10.gestores;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;

import org.json.JSONException;

import utn.dds.g10.entidades.*;
import utn.dds.g10.gestores.Buscador.HistorialConsultasFecha;

import utn.dds.g10.gestores.Buscador.HistorialConsultasUsuario;
import utn.dds.g10.gestores.Buscador.HistoricoProxy;

public class GestorPoi {

	private HistorialConsultas historial;
	private HistoricoProxy historicoProxy;
	private HistorialConsultasFecha historialFecha;
	private HistorialConsultasUsuario historialUsuario;
	ResultadoConsulta resultado;
	
	public GestorPoi(){
		historicoProxy = new HistoricoProxy();
		historial = new HistorialConsultas();
		historialFecha = new HistorialConsultasFecha();
		historialUsuario = new HistorialConsultasUsuario();		
	}


	public ResultadoConsulta BuscarPoi(String criterioBusqueda, String usuario) throws MalformedURLException, JSONException, IOException {
		resultado = new ResultadoConsulta();
		resultado = historicoProxy.BuscarPoi(criterioBusqueda);
		resultado.setUsuario(usuario);
		// Guardo la consulta en el Historial
		 historial.AgregarResultado(resultado);
		 historialFecha.AgregarResultado(resultado);
		 historialUsuario.AgregarResultado(resultado);

		return resultado;
	}

	public HistorialConsultasFecha getHistorialFecha() {
		return historialFecha;
	}


	public void setHistorialFecha(HistorialConsultasFecha historialFecha) {
		this.historialFecha = historialFecha;
	}


	public HistorialConsultasUsuario getHistorialUsuario() {
		return historialUsuario;
	}


	public void setHistorialUsuario(HistorialConsultasUsuario historialUsuario) {
		this.historialUsuario = historialUsuario;
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
	
	public HistorialConsultas listadoHistorialConsultas(){
		return historial;
	}
}
	
	

