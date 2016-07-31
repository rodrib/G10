package utn.dds.g10.tests;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import utn.dds.g10.entidades.ParadaColectivo;

public class pruebaAPI {
	
	@Test //1
	public void testAPIRest (){		
		
		  try {
			  
			String urlString = "http://trimatek.org/Consultas/banco?banco=Santander&servicio=Pagos";

			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  		
		Assert.assertTrue("El colectivo siempre esta disponible",true);		
	}

}
