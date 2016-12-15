package utn.dds.g10.gestores;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import utn.dds.g10.entidades.*;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.Buscador.HistorialConsultasFecha;
import utn.dds.g10.gestores.Buscador.HistorialConsultasUsuario;
import utn.dds.g10.gestores.Buscador.HistoricoProxy;

public class GestorPoi {

	private HistorialConsultas historial;
	private HistoricoProxy historicoProxy;
	private HistorialConsultasFecha historialFecha;
	private HistorialConsultasUsuario historialUsuario;
	ResultadoConsulta resultado;

	public GestorPoi(Usuario usuario) {
		historicoProxy = new HistoricoProxy(usuario);
		historial = new HistorialConsultas();
		historialFecha = new HistorialConsultasFecha();
		historialUsuario = new HistorialConsultasUsuario();
	}

	public ResultadoConsulta BuscarPoi(String criterioBusqueda, String usuario)
			throws MalformedURLException, JSONException, IOException {
		resultado = new ResultadoConsulta();
		resultado = historicoProxy.BuscarPoi(criterioBusqueda);
		resultado.setUsuario(usuario);
		
		filtrarResultado(resultado);
		
		// Guardo la consulta en el Historial
		historial.AgregarResultado(resultado);
		historialFecha.AgregarResultado(resultado);
		historialUsuario.AgregarResultado(resultado);
		
		return resultado;
	}
	
	public void filtrarResultado(ResultadoConsulta resultado){
	
		List<POI> resultadoPOISsinfiltrar = new ArrayList<POI>();
		List<POI> resultadoPOISfiltrado = new ArrayList<POI>();
		resultadoPOISsinfiltrar = resultado.getPuntos();
	
		for (int j = 0; j < (resultado.getCantidadResultados()); j++) {
			
			Long idPOI = resultadoPOISsinfiltrar.get(j).getId();
			int cantResultado = 0;
			for (int m = 0; m < (resultadoPOISfiltrado.size()); m++) {
				if (resultadoPOISfiltrado.get(m).getId() == (idPOI)) {
					cantResultado = 1;
				}
			}
			if (cantResultado == 0) {
				resultadoPOISfiltrado.add(resultadoPOISsinfiltrar.get(j));
			}
			
		}
		
		
		resultado.setPuntos(resultadoPOISfiltrado);
		resultado.setCantidadResultados(resultadoPOISfiltrado.size());
		
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

	public HistorialConsultas listadoHistorialConsultas() {
		return historial;
	}
}
