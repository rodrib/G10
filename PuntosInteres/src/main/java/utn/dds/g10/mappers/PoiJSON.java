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
			JSONObject jsonObjeto = null;
			
			//Realizo el mappeo.
			for (int i = 0; i < jsonArray.length(); i++) {				
				
				if (jsonObjeto.has("banco")){
						
					//Json array para los servicios del banco
					JSONArray jsonServiciosBancosArray = null;
					
					SucursalBanco miTipoPoiBanco = null;
					POI miPoiBanco = null;
					Locacion locacion = null;
					
					List<String> serviciosBanco = new ArrayList<String>();
					jsonObjeto = (JSONObject) jsonArray.get(i);
					
					
					miPoiBanco = new POI();
					miTipoPoiBanco = new SucursalBanco();
					
					locacion = new Locacion();
					locacion.setBarrio(jsonObjeto.get("sucursal").toString());
					
					miTipoPoiBanco.setNombreGerente(jsonObjeto.get("gerente").toString());
					//Cargo los servicios
					jsonServiciosBancosArray = new JSONArray(jsonObjeto.get("servicios").toString());				
					for (int j = 0; j < jsonServiciosBancosArray.length(); j++) {
						serviciosBanco.add(jsonServiciosBancosArray.get(j).toString());
					}
					miTipoPoiBanco.setServicios(serviciosBanco);
					
					miPoiBanco.setNombre(jsonObjeto.get("banco").toString());
					miPoiBanco.setLocacion(locacion);
					miPoiBanco.setTipo(miTipoPoiBanco);
					
					listaPois.add(miPoiBanco);
				
				}else if (jsonObjeto.has("comuna")){
					
					//Json array para los servicios del banco
					JSONArray jsonServiciosCGPArray = null;
					JSONArray jsonHorariosServiciosCGPArray = null;
					JSONObject jsonObjetoServicioCGP = null;
					JSONObject jsonObjetoHorario = null;
					
					CGP miTipoPoiCGP = null;
					POI miPoiCGP = null;
					Locacion locacion = null;
					ServicioCGP servicio = new ServicioCGP();
					List<ServicioCGP> serviciosBanco = new ArrayList<ServicioCGP>();
					jsonObjeto = (JSONObject) jsonArray.get(i);				
					miPoiCGP = new POI();
					miTipoPoiCGP = new CGP();			
					locacion = new Locacion();
					locacion.setCodigoComuna(Integer.parseInt(jsonObjeto.get("comuna").toString()));
					//Cargo las zonas
					miTipoPoiCGP.setZonas(jsonObjeto.get("zonas").toString()); 

					//Cargo los servicios
					jsonServiciosCGPArray = new JSONArray(jsonObjeto.get("servicios"));			
					
					for (int j = 0; j < jsonServiciosCGPArray.length(); j++) {
						ArrayList<Horarios> horarios = new ArrayList<Horarios>();
						jsonObjetoServicioCGP = jsonServiciosCGPArray.getJSONObject(j);
						servicio.setNombre(jsonObjetoServicioCGP.get("nombre").toString());
						jsonHorariosServiciosCGPArray = new JSONArray(jsonObjetoServicioCGP.get("horarios"));
						
						//Cargo los horarios
						for (int k = 0; k < jsonHorariosServiciosCGPArray.length(); k++) {
							jsonObjetoHorario = jsonHorariosServiciosCGPArray.getJSONObject(k);	
							Horarios horario = new Horarios();
							horario.setDiaSemana(Integer.parseInt(jsonObjetoHorario.get("diaSemana").toString()));
							horario.setHoraDesde(Integer.parseInt(jsonObjetoHorario.get("horaDesde").toString()));
							horario.setMinutosDesde(Integer.parseInt(jsonObjetoHorario.get("minutosDesde").toString()));
							horario.setHoraHasta(Integer.parseInt(jsonObjetoHorario.get("horaHasta").toString()));
							horario.setMinutosHasta(Integer.parseInt(jsonObjetoHorario.get("minutosHasta").toString()));
							horarios.add(horario);
						}
						servicio.setHorarios(horarios);
						serviciosBanco.add(servicio);
					}
					miTipoPoiCGP.setDirector(jsonObjeto.getString("director"));
					miTipoPoiCGP.setDomicilio(jsonObjeto.getString("domicilio"));
					miTipoPoiCGP.setTelefono(jsonObjeto.getString("telefono"));
					miPoiCGP.setNombre("CGP Comuna "+jsonObjeto.get("comuna").toString());
					miPoiCGP.setLocacion(locacion);
					miPoiCGP.setTipo(miTipoPoiCGP);
					listaPois.add(miPoiCGP);	
				}else if (jsonObjeto.has("local")){
					
					LocalComercial miTipoPoiLocal = null;
					POI miPoiLocal = null;
					Locacion locacion = null;
					
					jsonObjeto = (JSONObject) jsonArray.get(i);								
					miPoiLocal = new POI();
					miTipoPoiLocal = new LocalComercial();	
					locacion = new Locacion();
					
					if (jsonObjeto.get("tipo").toString().equalsIgnoreCase("kiosko")){					
						RubroLocal kiosko = new Kiosco();	
						miTipoPoiLocal.setRubro(kiosko);
					}else if (jsonObjeto.get("tipo").toString().equalsIgnoreCase("libreria")){
						RubroLocal libreria = new Libreria();	
						miTipoPoiLocal.setRubro(libreria);					
					}
					
					miPoiLocal.setNombre(jsonObjeto.get("local").toString());
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
