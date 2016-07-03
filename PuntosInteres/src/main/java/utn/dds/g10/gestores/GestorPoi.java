package utn.dds.g10.gestores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import utn.dds.g10.datos.PoiDatos;
import utn.dds.g10.entidades.*;

public class GestorPoi {

	HistorialConsultas historial = new HistorialConsultas();

	public ResultadoConsulta BuscarPoi(String criterioBusqueda) {
		PoiDatos datosPoi = new PoiDatos();
		List<POI> listadoPoiTodos = datosPoi.ObtenerPoiTodos();

		for (Iterator<POI> iterador = listadoPoiTodos.iterator(); iterador
				.hasNext();) {
			if (!CumpleCondicionBusqueda(iterador.next(), criterioBusqueda))
				iterador.remove();
		}

		// Retorna el resultado de una consulta.
		ResultadoConsulta resultado = new ResultadoConsulta();
		resultado.setPuntos(listadoPoiTodos);
		resultado.setFechaHora(LocalDate.now());

		// Guardo la consulta en el Historial
		// historial.AgregarResultado(resultado);

		return resultado;
	}

	private Boolean CumpleCondicionBusqueda(POI poi, String criterio) {

		// Cumple condicion en el nombre
		if (poi.getNombre().contains(criterio)) {
			return true;
		}

		if (poi.getTipo().CumpleCondicionBusqueda(criterio)) {
			return true;
		}

		// Busqueda en las palabras claves del poi

		if (poi.getPalabrasClaves() != null && !poi.getPalabrasClaves().isEmpty()) {
			for (String palabra : poi.getPalabrasClaves()) {
				if (palabra.equalsIgnoreCase(criterio)) {
					return true;
				}
			}
		}

		return false;
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
