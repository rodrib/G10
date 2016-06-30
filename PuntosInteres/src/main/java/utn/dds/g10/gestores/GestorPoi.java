package utn.dds.g10.gestores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import utn.dds.g10.datos.PoiDatos;
import utn.dds.g10.entidades.*;

public class GestorPoi {
	//
	// public listaPOIsSeleccionados buscarPOI(String tag) {
	// return new listaPOIsSeleccionados();
	// }

	public ResultadoConsulta BuscarPoi(String criterioBusqueda) {
		PoiDatos datosPoi = new PoiDatos();
		List<POI> listadoPoiTodos = datosPoi.ObtenerPoiTodos();

		for (Iterator<POI> it = listadoPoiTodos.iterator(); it.hasNext();) {
			if (!it.next().getNombre().contains(criterioBusqueda))
				it.remove();
		}

		// Retorna el resultado de una consulta.
		ResultadoConsulta resultado = new ResultadoConsulta();
		resultado.setPuntos(listadoPoiTodos);
		resultado.setFechaHora(LocalDate.now());

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
