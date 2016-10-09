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

	public static boolean AgregarPOI(POI poi) throws MalformedURLException, JSONException, IOException {
		
		POI poiBuscado=null;
		
		if (listadoPois == null) {
			listadoPois = PoiDatos.ObtenerPoiTodos();
		}
		
		for (POI poiElem : listadoPois){
			if (poiElem.getNombre().equalsIgnoreCase(poi.getNombre())){
				poiBuscado=poiElem;
				break;
			}
		}
		
		if (poiBuscado!=null)
			if (poiBuscado.getEstadoAlta()==false){
				listadoPois.remove(poiBuscado);
				poiBuscado.setEstadoAlta(true);
				return listadoPois.add(poiBuscado);
			}
		
		return listadoPois.add(poi);
	}
	
	public static POI EliminarPOI(POI poi) throws MalformedURLException, JSONException, IOException {
		
		POI poiBuscado=null;
		
		if (listadoPois == null) {
			listadoPois = PoiDatos.ObtenerPoiTodos();
		}
		
		for (POI poiElem : listadoPois){
			if (poiElem.getNombre().equalsIgnoreCase(poi.getNombre())){
				poiBuscado=poiElem;
				break;
			}
		}
		
		
		//No existe o ya fue borrado
		if (poiBuscado==null || poi.getEstadoAlta()==false){
			return null;
		}else{
			listadoPois.remove(poiBuscado);
			poiBuscado.setEstadoAlta(false);
		}
				
		listadoPois.add(poiBuscado);
		return poiBuscado;
		
//		return listadoPois.remove(poi);		
	}
	
	public static POI ModificarPOI(POI poi) throws MalformedURLException, JSONException, IOException {		
		POI poiBuscado=null;
		
		if (listadoPois == null) {
			listadoPois = PoiDatos.ObtenerPoiTodos();
		}
		
		for (POI poiElem : listadoPois){
			if (poiElem.getNombre().equalsIgnoreCase(poi.getNombre())){
				poiBuscado=poiElem;
				break;
			}
		}
		
		//No existe
		if (poiBuscado==null)
			return null;
		
  		listadoPois.remove(poiBuscado);
  		listadoPois.add(poi);
		
		return poiBuscado;
	}
}
