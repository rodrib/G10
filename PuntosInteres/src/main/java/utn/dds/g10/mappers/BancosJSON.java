package utn.dds.g10.mappers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utn.dds.g10.entidades.*;

public class BancosJSON {

	public static List<POI> obtenerBancos(String nombreBanco, String servicioBanco) throws MalformedURLException, IOException, JSONException {
		List<POI> listaBancosPois = new ArrayList<POI>();

		String urlGenerica = "http://localhost:8080/Consultas/banco";
		String urlString = "";
		
		if((nombreBanco == null || nombreBanco == "") && (servicioBanco == null || servicioBanco == "") )
		{
			urlString = urlGenerica;
		}
		else
		{
			urlString = urlGenerica + "?banco=" + nombreBanco + "&servicio=" + servicioBanco;	
		}		

		try {
			//Obtengo el JSON string.
			String jsonString = IOUtils.toString(new URL(urlString).openStream());
			
			//Convierto el JSON string en un un array de objetos JSON
			JSONArray jsonBancosArray = new JSONArray(jsonString);
			JSONObject jsonObjetoBanco = null;
			
			//Json array para los servicios del banco
			JSONArray jsonServiciosBancosArray = null;
			
			SucursalBanco miTipoPoiBanco = null;
			POI miPoiBanco = null;
			Locacion locacion = null;
			
			List<String> serviciosBanco = new ArrayList<String>();

			//Realizo el mappeo.
			for (int i = 0; i < jsonBancosArray.length(); i++) {
				
				jsonObjetoBanco = (JSONObject) jsonBancosArray.get(i);
				
				miPoiBanco = new POI();
				miTipoPoiBanco = new SucursalBanco();
				
				locacion = new Locacion();
				locacion.setBarrio(jsonObjetoBanco.get("sucursal").toString());
				
				miTipoPoiBanco.setNombreGerente(jsonObjetoBanco.get("gerente").toString());
				//Cargo los servicios
				jsonServiciosBancosArray = new JSONArray(jsonObjetoBanco.get("servicios").toString());				
				for (int j = 0; j < jsonServiciosBancosArray.length(); j++) {
					serviciosBanco.add(jsonServiciosBancosArray.get(j).toString());
				}
				miTipoPoiBanco.setServicios(serviciosBanco);
				
				miPoiBanco.setNombre(jsonObjetoBanco.get("banco").toString());
				miPoiBanco.setLocacion(locacion);
				miPoiBanco.setTipo(miTipoPoiBanco);
				
				listaBancosPois.add(miPoiBanco);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return listaBancosPois;
	}

	public static List<POI> obtenerBancos() throws MalformedURLException, IOException, JSONException
	{
		return obtenerBancos(null,null);
	}
}
