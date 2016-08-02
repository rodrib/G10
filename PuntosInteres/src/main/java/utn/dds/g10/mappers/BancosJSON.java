package utn.dds.g10.mappers;

import java.io.BufferedReader;
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
import utn.dds.g10.Utiles.*;

public class BancosJSON {

	public static List<SucursalBanco> obtenerBancos(String nombreBanco, String servicioBanco) throws MalformedURLException, IOException, JSONException {
		List<SucursalBanco> bancos = new ArrayList<SucursalBanco>();

		String urlGenerica = "http://trimatek.org/Consultas/banco";
		String urlString = urlGenerica + "?banco=" + nombreBanco + "&servicio=" + servicioBanco;

		try {
			//Obtengo el JSON string.
			String jsonString = IOUtils.toString(new URL(urlString).openStream());
			
			//Convierto el JSON string en un un array de objetos JSON
			JSONArray jasonArray = new JSONArray(jsonString);
			
			JSONObject objetoJson = null;
			SucursalBanco miBanco = null;

			//Realizo el mappeo.
			for (int i = 0; i < jasonArray.length(); i++) {
				objetoJson = (JSONObject) jasonArray.get(i);
				
				miBanco = new SucursalBanco();
				miBanco.setNombre(objetoJson.get("banco").toString());
				miBanco.setNombreSucursal(objetoJson.get("sucursal").toString());
				miBanco.setNombreGerente(objetoJson.get("gerente").toString());

				bancos.add(miBanco);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return bancos;
	}
}
