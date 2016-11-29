package utn.dds.g10.gestores.Buscador;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.HistorialConsultas;
import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.SucursalBanco;


public class BuscadorPoi implements Buscador {
	
HistorialConsultas historial = new HistorialConsultas();
	
public  ResultadoConsulta BuscarPoi(String criterioBusqueda) throws MalformedURLException, JSONException, IOException {
		
		List<POI> listadoPoiInicial=  (ArrayList<POI>) DaoRelacional.obtenerPois();
		List<POI> listadoPoi =  new ArrayList<POI>();
		for (Iterator<POI> iterador = listadoPoiInicial.iterator(); iterador
				.hasNext();) {
			
			POI poiObtenido = new POI();
			poiObtenido = iterador.next();
			
			System.out.println("Nombre POI: "+poiObtenido.getNombre());
			obtenerPOI(poiObtenido);	
			
			if (CumpleCondicionBusqueda(poiObtenido, criterioBusqueda))
				listadoPoi.add(poiObtenido);
		}

		// Retorna el resultado de una consulta.
		ResultadoConsulta resultado = new ResultadoConsulta();
		resultado.setPuntos(listadoPoi);
		resultado.setFechaHora(LocalDate.now());

		return resultado;
	}

private POI obtenerPOI(POI poi){
	POI poiReconstruido = new POI();
	poiReconstruido.setEstadoAlta(poi.getEstadoAlta());
	poiReconstruido.setId(poi.getId());
	poiReconstruido.setLocacion(poi.getLocacion());
	poiReconstruido.setNombre(poi.getNombre());
	poiReconstruido.setPalabrasClaves(poi.getPalabrasClaves());
	
	if (poi.getTipo().tipoPOI().equalsIgnoreCase("CGP")){
		CGP cgp = new CGP();
		cgp=(CGP)poi.getTipo();
		poiReconstruido.setTipo(cgp);
	}else if (poi.getTipo().tipoPOI().equalsIgnoreCase("SucursalBanco")){
		SucursalBanco banco = new SucursalBanco();
		banco=(SucursalBanco)poi.getTipo();
		poiReconstruido.setTipo(banco);
	}else if (poi.getTipo().tipoPOI().equalsIgnoreCase("ParadaColectivo")){
		ParadaColectivo parada = new ParadaColectivo();
		parada=(ParadaColectivo)poi.getTipo();
		poiReconstruido.setTipo(parada);
		
	}else if (poi.getTipo().tipoPOI().equalsIgnoreCase("LocalComercial")){
		LocalComercial local = new LocalComercial();
		local=(LocalComercial)poi.getTipo();
		Kiosco kiosco = new Kiosco();
		Libreria libreria = new Libreria();
		if (local.getRubro().tipoRubro().equalsIgnoreCase("Kiosco")){
			kiosco=(Kiosco)local.getRubro();
			local.setRubro(kiosco);
		}else if (local.getRubro().tipoRubro().equalsIgnoreCase("Libreria")){
			libreria=(Libreria)local.getRubro();
			local.setRubro(libreria);
		}
		poiReconstruido.setTipo(local);
	}	
	return poiReconstruido;
}


private  Boolean CumpleCondicionBusqueda(POI poi, String criterio) {

	if (poi.getEstadoAlta()==true){
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
	}

	return false;
}


}