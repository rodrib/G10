package utn.dds.g10.mappers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import com.google.gson.Gson;


import utn.dds.g10.entidades.*;

public class CGPJSON {

	public static List<POI> obtenerListadoPOICGP() throws MalformedURLException, IOException, JSONException {
		
		CentroDTO centroDTO = null;
		List<POI> listadoPoiCGP= new ArrayList<POI>();
		POI poi= new POI();
		Gson gson = new Gson();	
		Locacion locacion = new Locacion();
		
		String urlGenerica = "http://trimatek.org/Consultas/centro";
//		String urlString = urlGenerica + "?zona=" + zona ;

		try {
			String jsonString = IOUtils.toString(new URL(urlGenerica).openStream());
			
			String jsonArmado = "{listaCGP:"+jsonString+"}";			
								
			centroDTO = gson.fromJson(jsonArmado, CentroDTO.class);						
		    System.out.println(gson.toJson(centroDTO));
		    		    
		    
			List<CGP> cgp = centroDTO.getListaCGP();
			
			Iterator<CGP> i = cgp.iterator();
			
			while(i.hasNext()){
				CGP cgpElemento = (CGP) i.next();			
				locacion.setBarrio(cgpElemento.getZonas());
				poi.setLocacion(locacion);
				poi.setTipo(cgpElemento);
				listadoPoiCGP.add(poi);
			}		   
		    
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listadoPoiCGP;
	}
}
