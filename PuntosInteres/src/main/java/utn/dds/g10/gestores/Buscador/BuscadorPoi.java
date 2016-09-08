package utn.dds.g10.gestores.Buscador;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.HistorialConsultas;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;


public class BuscadorPoi implements Buscador {
	
HistorialConsultas historial = new HistorialConsultas();
	
public  ResultadoConsulta BuscarPoi(String criterioBusqueda) throws MalformedURLException, JSONException, IOException {
		
		ArrayList<POI> listadoPoiTodos = (ArrayList<POI>)Repositorio.getInstance().getDatos();
		
		List<POI> listadoPoiTodosFinal =  (ArrayList<POI>) listadoPoiTodos.clone();

		for (Iterator<POI> iterador = listadoPoiTodosFinal.iterator(); iterador
				.hasNext();) {
			if (!CumpleCondicionBusqueda(iterador.next(), criterioBusqueda))
				iterador.remove();
		}

		// Retorna el resultado de una consulta.
		ResultadoConsulta resultado = new ResultadoConsulta();
		resultado.setPuntos(listadoPoiTodosFinal);
		resultado.setFechaHora(LocalDate.now());

		return resultado;
	}

private  Boolean CumpleCondicionBusqueda(POI poi, String criterio) {

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


}
