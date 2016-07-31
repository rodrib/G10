package utn.dds.g10.mappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utn.dds.g10.entidades.*;
import utn.dds.g10.Utiles.*;


public class BancosJSON {
	
	public List<SucursalBanco> obtenerBancos(String nombreBanco, String servicioBanco) throws IOException
	{
		List<SucursalBanco> bancos = new ArrayList<SucursalBanco>();
		
		String urlGenerica = "http://trimatek.org/Consultas/banco";
		String urlString =  urlGenerica + "?banco="+ nombreBanco +"&servicio=" + servicioBanco;
		BufferedReader reader = ConexionJSON.obtenerJSON(urlString);
		
		String  output;
		while ((output = br.readLine()) != null) {
			
			
			
		}
		
		//Aca hay que hacer el mapeo del Json a la lista de sucursales de banco. 
		//
		//
		//Magia
		//
		//
		
		return bancos;
		
	} 

}
