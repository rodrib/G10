package utn.dds.g10.datos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Kiosco;
import utn.dds.g10.entidades.Libreria;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.mappers.BancosJSON;
import utn.dds.g10.mappers.CGPJSON;

public class PoiDatos {

	public PoiDatos() throws MalformedURLException, JSONException, IOException
	{

	}
	
	public static List<POI> ObtenerPoiTodos() throws MalformedURLException, JSONException, IOException

	{
		List<POI> listadoPoi = new ArrayList<POI>();;
		POI miPoi; 
		
		//AGREGO BANCOS DEL SERVICIO		
		listadoPoi.addAll(BancosJSON.obtenerBancos());
		//AGREGO BANCOS DEL SERVICIO		
		listadoPoi.addAll(CGPJSON.obtenerListadoPOICGP());
		
		///BANCOS
		miPoi = new POI();
		Locacion locacionn=new Locacion();
		SucursalBanco sucBanco = new SucursalBanco();
		sucBanco.setNombreGerente("Alexis Aostri");
		ArrayList<String> listaServiciosBanco = new ArrayList<String>();
		listaServiciosBanco.add("Comercio Exterior");
		listaServiciosBanco.add("Cuentas Bancarias");		
		sucBanco.setServicios(listaServiciosBanco);
		locacionn.setDireccion("Billinghurst 2143");
		locacionn.setBarrio("Recoleta");
		miPoi.setLocacion(locacionn);
		miPoi.setNombre("Banco Frances");
		miPoi.setTipo(sucBanco);
		miPoi.setId(1L);
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		Locacion locacionn2=new Locacion();
		SucursalBanco sucBanco2 = new SucursalBanco();
		ArrayList<String> listaServiciosBanco2 = new ArrayList<String>();
		listaServiciosBanco2.add("Comercio Exteriorr");
		listaServiciosBanco2.add("Cuentas Bancariass");
		sucBanco2.setServicios(listaServiciosBanco2);
		sucBanco2.setNombreGerente("Lionel Messi");
		locacionn2.setDireccion("French 2143");
		locacionn2.setBarrio("Palermo");
		miPoi.setLocacion(locacionn2);
		miPoi.setNombre("Banco ITAU");
		miPoi.setTipo(sucBanco2);
		miPoi.setId(2L);
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		SucursalBanco sucBanco3 = new SucursalBanco();
		sucBanco3.setNombreGerente("Mauricio Macri");
		ArrayList<String> listaServiciosBanco3 = new ArrayList<String>();
		listaServiciosBanco3.add("Comercio Exteriorrrr");
		listaServiciosBanco3.add("Cuentas Bancariasssss");		
		sucBanco3.setServicios(listaServiciosBanco3);
		locacionn2.setDireccion("Las Heras 2143");
		locacionn2.setBarrio("Belgrano");
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Banco Frances");
		miPoi.setTipo(sucBanco3);
		miPoi.setId(3L);
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		Locacion locacionn3=new Locacion();
		locacionn3.setDireccion("Aguero 4444");
		miPoi.setLocacion(locacionn3);
		miPoi.setNombre("Banco ICBC");
		miPoi.setTipo(new SucursalBanco());
		miPoi.setId(4L);
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		Locacion locacionn4=new Locacion();
		locacionn4.setDireccion("Suipacha 666");
		miPoi.setLocacion(locacionn4);
		miPoi.setNombre("Banco HSBC");
		miPoi.setTipo(new SucursalBanco());
		miPoi.setId(5L);
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		Locacion locacionCGP= new Locacion();
		locacionCGP.setDireccion("Avenida siempre viva");
		locacionCGP.setBarrio("Springfield");
		miPoi.setLocacion(locacionCGP);
		miPoi.setNombre("CGP Comuna 3");
		CGP cgp = new CGP();
		cgp.setZonas("zona1");
		ServicioCGP servicioCGP = new ServicioCGP();
		servicioCGP.setNombre("tramite");
		ServicioCGP servicioCGP2 = new ServicioCGP();
		servicioCGP2.setNombre("tramiteee2");
		ArrayList<ServicioCGP> listaServicios = new ArrayList<ServicioCGP>();
		listaServicios.add(servicioCGP);
		listaServicios.add(servicioCGP2);
		cgp.setServicios(listaServicios);
		miPoi.setTipo(cgp);
		miPoi.setId(6L);
		listadoPoi.add(miPoi);
		
		////////KIOSCOS
		miPoi = new POI();
		Locacion locacionLocal = new Locacion();
		locacionLocal.setDireccion("Laguna 555");
		miPoi.setLocacion(locacionLocal);
		miPoi.setNombre("Kiosco MO");
		ArrayList<String> ListaPalabrasClave = new ArrayList<String>();
		ListaPalabrasClave.add("caramelos");
		miPoi.setPalabrasClaves(ListaPalabrasClave);
		miPoi.setTipo(new LocalComercial());
		miPoi.setId(7L);
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		Locacion locacionLocal2 = new Locacion();
		locacionLocal2.setDireccion("santa fe 111");
		miPoi.setLocacion(locacionLocal2);
		miPoi.setNombre("Kiosco SI");
		miPoi.setTipo(new LocalComercial());
		miPoi.setId(8L);
		listadoPoi.add(miPoi);
		
		////////COLECTIVOS
		miPoi = new POI();
		Locacion locacionColectivo = new Locacion();
		locacionColectivo.setCallePrincipal("Diagonal Norte");
		miPoi.setLocacion(locacionColectivo);
		miPoi.setNombre("Parada114");
		miPoi.setTipo(new ParadaColectivo());
		miPoi.setId(9L);
		listadoPoi.add(miPoi);
		
		miPoi = new POI();
		miPoi.setLocacion(new Locacion());
		miPoi.setNombre("Parada25");
		miPoi.setTipo(new ParadaColectivo());
		miPoi.setId(10L);
		listadoPoi.add(miPoi);		
		
		LocalComercial localLibreria = new LocalComercial();
		localLibreria.setRubro(new Libreria());
		
		POI libreria = new POI();
		libreria.setLocacion(new Locacion());
		libreria.setNombre("lapices");
		libreria.setTipo(localLibreria);
		libreria.setId(11L);
		listadoPoi.add(libreria);
		
		
		LocalComercial localKiosco = new LocalComercial();
		localKiosco.setRubro(new Kiosco());
		
		POI localConDescuento = new POI();
		Locacion locacion24 = new Locacion();
		locacion24.setDireccion("Florida 99");
		locacion24.setProvincia("CABA");
		localConDescuento.setLocacion(locacion24);
		localConDescuento.setNombre("24hs");
		localConDescuento.setTipo(localKiosco);
		localConDescuento.setId(12L);
		
		ArrayList<String> palabrasPoi = new ArrayList<String>();
		palabrasPoi.add("Viernes");
		palabrasPoi.add("Descuentos");
		localConDescuento.setPalabrasClaves(palabrasPoi);
		
		listadoPoi.add(localConDescuento);
		
		
		return listadoPoi;
		
	}
	
	
}

