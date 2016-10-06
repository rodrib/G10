package utn.dds.g10.gestores.Buscador;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;

public class BuscadorLocalComercial {
	
	public ResultadoConsulta BuscarLocalComercial(String criterioBusqueda) throws MalformedURLException, JSONException, IOException {
			
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
			resultado.setCantidadResultados();
			return resultado;
		}

	private  Boolean CumpleCondicionBusqueda(POI poi, String criterio) {
		System.out.println(criterio);
		System.out.println(poi.getNombre());
		// Cumple condicion en el nombre
		if (poi.getNombre().contains(criterio)) {
			System.out.println("True igual nombre");
			return true;
		}

		if (poi.getTipo().CumpleCondicionBusqueda(criterio)) {
			System.out.println("True cumple condicion");
			return true;
		}

		return false;
	}
	
}
