package utn.dds.g10.datos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import utn.dds.g10.entidades.POI;

//Clase que representa una fuente de datos. 
public class Repositorio {

	private static Repositorio instance = null;
	private static List<POI> listadoPois = null;;
	private static List<POI> listadoPoisAuxiliar = new ArrayList<POI>();


	// Constructor privado para que no se pueda instanciar desde afuera.
	private Repositorio() {
	}

	public static Repositorio getInstance() {
		// Solo se instancia una vez
		if (instance == null) {
			instance = new Repositorio();
		}
		return instance;
	}

	public List<POI> getDatos() throws MalformedURLException, JSONException,
			IOException {
		// Solo se instancia una vez
		if (listadoPois == null) {
			listadoPois = PoiDatos.ObtenerPoiTodos();			
			return listadoPois;
		}else{
			for(POI poi : listadoPois){
				if(poi.getEstadoAlta() == true){
					listadoPoisAuxiliar.add(poi);
				}
			}
			return listadoPoisAuxiliar;
		}		
	}

	public static boolean AgregarPOI(POI poi) {
		return listadoPois.add(poi);
	}
	
	public static boolean EliminarPOI(POI poi) {
		
		
		int indice=listadoPois.indexOf(poi);
		//No existe
		if (indice==-1 || poi.getEstadoAlta()==false){
			return false;
		}else{
			poi.setEstadoAlta(false);
		}
				
		listadoPois.set(indice,poi);
		return true;
		
//		return listadoPois.remove(poi);		
	}
	
	public static boolean ModificarPOI(POI poi) {		
		int indice=listadoPois.indexOf(poi);
		//No existe
		if (indice==-1)
			return false;
		
		listadoPois.set(indice,poi);
		return true;
	}
}
