package utn.dds.g10.beans;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
//import utn.dds.g10.entidades.*;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.json.JSONException;

@ManagedBean(name="busqueda")
@SessionScoped
public class BusquedaBean implements Serializable{
	
	Usuario usuario;
	GestorPoi gestorPoi;
	POI poiElegido;
	String criterioBusqueda;

	public POI getPoiElegido() {
		return poiElegido;
	}

	public void setPoiElegido(POI poiElegido) {
		this.poiElegido = poiElegido;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static ArrayList<String> getCriterioslist() {
		return criteriosList;
	}

	private static final long serialVersionUID = 1L;

	private static final ArrayList<String> criteriosList =
		new ArrayList<String>();

	public ArrayList<String> getCriteriosList() {
		return criteriosList;
	}
		
	private static final ArrayList<POI> poiList =
			new ArrayList<POI>();

	public ArrayList<POI> getPoiList() {
		return poiList;
	}

	public void addAction() {
		if(getCriterioBusqueda()!=""){
			criteriosList.add(criterioBusqueda);
		}
	}
	
	public void searchAction(String nombreUsuario) throws MalformedURLException, JSONException, IOException {
		
		usuario = new Usuario();
		RolAdministrador rolAdministrador = new RolAdministrador();
		usuario.setRol(rolAdministrador);
		
		//Inicializo Gestor
		gestorPoi = new GestorPoi(usuario);
		ResultadoConsulta resultado;
		for (String criterioBusqueda:getCriterioslist()){
			
			resultado = gestorPoi.BuscarPoi(criterioBusqueda, nombreUsuario);
			List<POI> listadoPoi = resultado.getPuntos();
			if (resultado.getCantidadResultados() != 0){
				System.out.println(resultado.getCantidadResultados());
				System.out.println(resultado.getPuntos().get(0).getNombre());			
					
					for (int j = 0; j < (resultado.getCantidadResultados()); j++) {
						Long idPOI = listadoPoi.get(j).getId();
							int cantResultado= 0;
							for (int m = 0; m < (poiList.size()); m++) {
								if(poiList.get(m).getId()==(idPOI)){
									cantResultado =1;
								}								
							}
							if(cantResultado==0){
								poiList.add(listadoPoi.get(j));								
							}						
					}					
			}
			
		}
		
	}
}