package utn.dds.g10.mappers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utn.dds.g10.Utiles.LeerFichero;
import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Horarios;
import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.RubroLocal;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;

public class PoiJSON {

	public static List<POI> obtenerPois(String nombreArchivo) throws MalformedURLException, IOException, JSONException {
		List<POI> listaPois = new ArrayList<POI>();

		try {
			
			//Obtengo el JSON string.
			String jsonString = LeerFichero.devuelveJSON(nombreArchivo);
			
			//Convierto el JSON string en un un array de objetos JSON
			JSONArray jsonArray = new JSONArray(jsonString);
			JSONObject jsonObjeto = new JSONObject();
			
			//Realizo el mappeo.
			for (int i = 0; i < jsonArray.length(); i++) {				
				
				jsonObjeto = (JSONObject) jsonArray.get(i);
				
				String tipoPoi = jsonObjeto.get("poi").toString();
				
				if (tipoPoi.equalsIgnoreCase("banco")){
					
					SucursalBanco miTipoPoiBanco = new SucursalBanco();
					POI miPoiBanco = new POI();
					Locacion locacion = new Locacion();		

					miPoiBanco.setNombre(jsonObjeto.get("nombre").toString());
					miPoiBanco.setLocacion(locacion);
					miPoiBanco.setTipo(miTipoPoiBanco);
					
					listaPois.add(miPoiBanco);
				
				}else if (tipoPoi.equalsIgnoreCase("comuna")){
					
					CGP miTipoPoiCGP = new CGP();
					POI miPoiCGP = new POI();
					Locacion locacion = new Locacion();	

					locacion.setCodigoComuna(Integer.parseInt(jsonObjeto.get("nombre").toString()));
					
					miPoiCGP.setNombre("CGP Comuna "+jsonObjeto.get("nombre").toString());
					miPoiCGP.setLocacion(locacion);
					miPoiCGP.setTipo(miTipoPoiCGP);
					listaPois.add(miPoiCGP);	
				}else if (tipoPoi.equalsIgnoreCase("local")){
					
					LocalComercial miTipoPoiLocal = new LocalComercial();
					POI miPoiLocal = new POI();
					Locacion locacion = new Locacion();									
					
					miPoiLocal.setNombre(jsonObjeto.get("nombre").toString());
					miPoiLocal.setLocacion(locacion);
					miPoiLocal.setTipo(miTipoPoiLocal);
					
					listaPois.add(miPoiLocal);
					
				}			
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listaPois;
	}
	
}
