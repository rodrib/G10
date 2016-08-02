package utn.dds.g10.tests;

//import java.io.*;
//import java.time.LocalDateTime;
//
//import org.json.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.mappers.BancosJSON;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.io.*;
//import org.apache.
//import utn.dds.g10.entidades.ParadaColectivo;
//import utn.dds.g10.mappers.BancosJSON;
//
//import java.net.*;
//
//import org.json.JSONObject;

public class pruebaAPI {

	@Test
	public void test() throws MalformedURLException, IOException, JSONException {

		try {
			

			String url = "http://trimatek.org/Consultas/banco?banco=Santander&servicio=Pagos";
			String genreJson = IOUtils.toString(new URL(url).openStream());
			JSONArray arrayjs = new JSONArray(genreJson);
			
			JSONObject firstGenre = (JSONObject) arrayjs.get(0);
			System.out.println(firstGenre.get("banco"));
			System.out.println(firstGenre.get("sucursal"));
			System.out.println(firstGenre.get("gerente"));

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testTraerBancos()throws MalformedURLException, IOException, JSONException
	{
		List<SucursalBanco> bancos = BancosJSON.obtenerBancos("Santander", "Pagos");
	}
}
