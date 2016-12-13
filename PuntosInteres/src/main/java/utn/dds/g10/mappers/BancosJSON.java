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

import utn.dds.g10.Utiles.Configuraciones;
import utn.dds.g10.entidades.*;

public class BancosJSON {
	
	enum tipoBusqueda {soloNombre, soloServicio, ambos, ninguno};

	public static List<POI> obtenerBancos(String nombreBanco, String servicioBanco) throws MalformedURLException, IOException, JSONException {
		List<POI> listaBancosPois = new ArrayList<POI>();

		//String urlGenerica = Configuraciones.obtenerUrlBancos();
		String urlGenerica = "http://trimatek.org/Consultas/banco";
		String urlString = "";
		
		tipoBusqueda tipoBusque = tipoBusqueda.ninguno;
		if((nombreBanco == null || nombreBanco == "") && (servicioBanco == null || servicioBanco == ""))
		{
			tipoBusque = tipoBusqueda.ninguno;
		}
		
		if((nombreBanco != null && nombreBanco != "") && (servicioBanco == null || servicioBanco == ""))
		{
			tipoBusque = tipoBusqueda.soloNombre;
		}
		
		if((nombreBanco == null || nombreBanco == "") && (servicioBanco != null && servicioBanco != ""))
		{
			tipoBusque = tipoBusqueda.soloServicio;
		}
		
		if((nombreBanco != null && nombreBanco != "") && (servicioBanco != null && servicioBanco != ""))
		{
			tipoBusque = tipoBusqueda.ambos;
		}
		
		switch (tipoBusque) {
		case ninguno:
			urlString = urlGenerica;
			break;
		case ambos:
			urlString = urlGenerica + "?banco=" + nombreBanco + "&servicio=" + servicioBanco;	
			break;
		case soloNombre:
			urlString = urlGenerica + "?banco=" + nombreBanco;	
			break;
		case soloServicio:
			urlString = urlGenerica + "?servicio=" + servicioBanco;	
			break;
		default:
			break;
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
			
			ArrayList<String> serviciosBanco = new ArrayList<String>();

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
	
	public static POI getPoiBancoFromJsonMongo(String jsonBancoString)
	{
		POI poibanco = new POI();
		SucursalBanco sucursal = new SucursalBanco();
		Locacion locacionBanco = new Locacion();
		List<String> serviciosBanco = new ArrayList<String>();
		
		JSONObject jsonObjetoBanco = new JSONObject(jsonBancoString);
		JSONObject jsonLocacion = jsonObjetoBanco.getJSONObject("locacion");
		JSONArray  jsonPalabrasClaves = jsonObjetoBanco.getJSONArray("palabrasClaves");
		JSONArray jsonresultados = jsonObjetoBanco.getJSONArray("resultados");
		JSONObject jsontipo = jsonObjetoBanco.getJSONObject("tipo");
		
		JSONArray jsonSericios = jsontipo.getJSONArray("servicios");
		
		for (int j = 0; j < jsonSericios.length(); j++) {
			serviciosBanco.add(jsonSericios.get(j).toString());
		}
		
		locacionBanco.setBarrio(jsonLocacion.get("barrio").toString());
		locacionBanco.setCodigoComuna(Integer.parseInt(jsonLocacion.get("codigoComuna").toString()));
		locacionBanco.setCodigoPostal(Integer.parseInt(jsonLocacion.get("codigoPostal").toString()));
		locacionBanco.setNumero(Integer.parseInt(jsonLocacion.get("numero").toString()));		
		
		sucursal.setNombreGerente(jsontipo.get("nombreGerente").toString());
		sucursal.setServicios(serviciosBanco);
		
		poibanco.setNombre(jsonObjetoBanco.get("nombre").toString());
		poibanco.setEstadoAlta(true);
		poibanco.setTipo(sucursal);
		poibanco.setLocacion(locacionBanco);
		
		
		return poibanco;
	}
}
