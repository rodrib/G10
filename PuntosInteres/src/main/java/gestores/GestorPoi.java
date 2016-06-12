package gestores;


import java.time.LocalDateTime;

import clases.*;

public class GestorPoi {

	public ResultadoConsulta ObtenerPoiPorCriterio(String textoLibre) {
		return new ResultadoConsulta();
	}

	public float CalcularDistanciaEntrePuntos(POI puntoUno, POI puntoDos) {
		return Utiles.Util.CalcularDistancia(puntoUno.getLocacion(), puntoDos.getLocacion());
	}	
	
	public boolean EstaCerca(POI poiUno, POI poiDos)
	{
		return poiUno.getTipo().estaCerca(poiUno.getLocacion(), poiDos.getLocacion());
	}
	
	public boolean EstaCerca(POI poiUno, Locacion miLocacion)
	{
		return poiUno.getTipo().estaCerca(poiUno.getLocacion(), miLocacion);
	}
	
	public boolean EstaDisponible(POI poi, LocalDateTime fecha)
	{
		return poi.getTipo().estaDisponible(fecha);
	}
}
