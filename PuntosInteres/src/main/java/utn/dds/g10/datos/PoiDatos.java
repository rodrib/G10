package utn.dds.g10.datos;

import java.util.LinkedList;
import java.util.List;

import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.LocalComercial;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.SucursalBanco;

public class PoiDatos {
	
	
//	public static List<POI> buscarPOI (String tag){
//		List<POI> listaPOIsSeleccionados = new ArrayList <POI>();
//		for(int i=0;i<listaPOIs.size();i++) {
//			if (listaPOIs.get(i).buscarTag(tag)){
//				listaPOIsSeleccionados.add(listaPOIs.get(i));
//			}
//		}
//		return listaPOIsSeleccionados;
//	}
	
	public List<POI> ObtenerPoiTodos()
	{
		List<POI> listadoPoi = new LinkedList<POI>();;
		POI miPoi; 
		
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
		
		return listadoPoi;
		
	}
}
