package gestores;

import clases.*;

public class GestorPoi {
	
	public ResultadoConsulta ObtenerPoiPorCriterio(String criterio) {
		return new ResultadoConsulta();
	}
	
	public float CalcularDistanciaEntrePuntos(POI puntoUno, POI puntoDos)
	{
		return puntoDos.getLocacion().getCoordenada().getLatitud();
	}
}
