package utn.dds.g10.gestores;


import java.time.LocalDateTime;

import utn.dds.g10.entidades.*;

public class GestorPoi {

	public ResultadoConsulta ObtenerPoiPorCriterio(String textoLibre) {
		return new ResultadoConsulta();
	}

	public float CalcularDistanciaEntrePuntos(POI puntoUno, POI puntoDos) {
		return utn.dds.g10.Utiles.Util.CalcularDistancia(puntoUno.getLocacion(), puntoDos.getLocacion());
	}	
	
	public boolean EstaCerca(POI poiUno, POI poiDos)
	{
		return this.EstaCerca(poiUno, poiDos.getLocacion());
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
