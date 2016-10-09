package utn.dds.g10.model;

import java.util.ArrayList;
import java.util.List;

public class ResultadoProcesos {

	private static List<ElementoResultadoProceso> listadoResultadoProcesos = new ArrayList<ElementoResultadoProceso>();

	public static List<ElementoResultadoProceso> getListadoResultadoProcesos() {
		return listadoResultadoProcesos;
	}

	public static void setListadoResultadoProcesos(
			List<ElementoResultadoProceso> listadoResultadoProcesos) {
		ResultadoProcesos.listadoResultadoProcesos = listadoResultadoProcesos;
	}
	
}
