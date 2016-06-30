package utn.dds.g10.gestores;


import java.time.LocalDateTime;

import utn.dds.g10.entidades.*;

public class GestorPoi {
//
//	public listaPOIsSeleccionados buscarPOI(String tag) {
//		return new listaPOIsSeleccionados();
//	}
	
	public ResultadoConsulta BuscarPoi(String criterioBusqueda)
	{
		ResultadoConsulta resultado = null;
		return resultado;
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
	
	public boolean EstaDisponible(POI poi, LocalDateTime fecha, String x)
	{
		return poi.getTipo().estaDisponible(fecha, x);
	}
}
