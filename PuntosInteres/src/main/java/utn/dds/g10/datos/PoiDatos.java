package utn.dds.g10.datos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;

import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.mappers.BancosJSON;
import utn.dds.g10.mappers.CGPJSON;

public class PoiDatos {

	public static List<POI> ListaPois;
	
	public PoiDatos() throws MalformedURLException, JSONException, IOException
	{
		
		ListaPois = this.ObtenerPoiTodos();
	
	}
	
	public List<POI> ObtenerPoiTodos() throws MalformedURLException, JSONException, IOException

	{
		List<POI> listadoPoi = new ArrayList<POI>();;
		POI miPoi; 
		
		//AGREGO BANCOS DEL SERVICIO		
		listadoPoi.addAll(BancosJSON.obtenerBancos());
		//AGREGO BANCOS DEL SERVICIO		
		listadoPoi.addAll(CGPJSON.obtenerListadoPOICGP());
		
		///BANCOS
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Banco Frances");
		miPoi.setTipo(new SucursalBanco());
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Banco Frances");
		miPoi.setTipo(new SucursalBanco());
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Banco Frances");
		miPoi.setTipo(new SucursalBanco());
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Banco ICBC");
		miPoi.setTipo(new SucursalBanco());
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Banco HSBC");
		miPoi.setTipo(new SucursalBanco());
		listadoPoi.add(miPoi);
		
		////////KIOSCOS
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Kiosco MO");
		miPoi.setTipo(new LocalComercial());
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Kiosco SI");
		miPoi.setTipo(new LocalComercial());
		listadoPoi.add(miPoi);
		
		////////COLECTIVOS
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Parada 114");
		miPoi.setTipo(new ParadaColectivo());
		listadoPoi.add(miPoi);
		
		
		LocalComercial localLibreria = new LocalComercial();
		localLibreria.setRubro(new Libreria());
		
		POI libreria = new POI();
		libreria.setLocacion(new Locacion());
		libreria.setNombre("lapices");
		libreria.setTipo(localLibreria);
		listadoPoi.add(libreria);
		
		
		LocalComercial localKiosco = new LocalComercial();
		localKiosco.setRubro(new Kiosco());
		
		POI localConDescuento = new POI();
		localConDescuento.setLocacion(new Locacion());
		localConDescuento.setNombre("24hs");
		localConDescuento.setTipo(localKiosco);
		
		List<String> palabrasPoi = new LinkedList<String>();
		palabrasPoi.add("Viernes");
		palabrasPoi.add("Descuentos");
		localConDescuento.setPalabrasClaves(palabrasPoi);
		
		listadoPoi.add(localConDescuento);
		
		
		return listadoPoi;
		
	}

	public static boolean AgregarPOI(POI poi){
		ListaPois.add(poi);
		return true;
		
	}
	
	public static boolean EliminarPOI(POI poi){
		ListaPois.remove(poi);
		return true;
	}
	
	public static boolean ModificarPOI(POI poi, POI poiNuevo){
		ListaPois.set(ListaPois.indexOf((POI) poi) , poiNuevo);
		return true;
		
	}
	
	
}

