package utn.dds.g10.beans;

import utn.dds.g10.DAO.DaoRelacional;
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
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.json.JSONException;

@ManagedBean(name = "busqueda")
@ApplicationScoped
public class BusquedaBean implements Serializable {

	Usuario usuario;
	GestorPoi gestorPoi;
	POI poiElegido;
	String criterioBusqueda;
	ResultadoConsulta resultado;

	@ManagedProperty(value = "#{navbarbean}")
	private NavBarBean navigationBar;

	public NavBarBean getNavigationBar() {
		return navigationBar;
	}

	public void setNavigationBar(NavBarBean navigationBar) {
		this.navigationBar = navigationBar;
	}

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

	private static  long serialVersionUID = 1L;

	private static  ArrayList<String> criteriosList = new ArrayList<String>();

	public ArrayList<String> getCriteriosList() {
		return criteriosList;
	}

	private static  ArrayList<POI> poiList = new ArrayList<POI>();

	public ArrayList<POI> getPoiList() {
		return poiList;
	}

	public void addAction() {
		boolean existe = false;

		if (getCriterioBusqueda() != "") {
			for (String criterio : criteriosList) {
				if (criterio.equals(criterioBusqueda)) {
					existe = true;
				}
			}
			if (!existe) {
				criteriosList.add(criterioBusqueda);
			}
		}
	}

	public void Limpiar() {
		criteriosList = new ArrayList<String>();
		poiList = new ArrayList<POI>();
	}

	public void searchAction(String nombreUsuario)
			throws MalformedURLException, JSONException, IOException {

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String username_log = (String)sessionMap.get("nombre_usuario");
		Usuario usLogueado = DaoRelacional.obtenerUsuariosPorNombre(username_log, Usuario.class);		
		gestorPoi = new GestorPoi(usLogueado);
		
		for (String criterioBusqueda : getCriterioslist()) {
			resultado = gestorPoi.BuscarPoi(criterioBusqueda, username_log);
			List<POI> listadoPoi = resultado.getPuntos();
			
			if (resultado.getCantidadResultados() != 0) {
				System.out.println(resultado.getCantidadResultados());
				System.out.println(resultado.getPuntos().get(0).getNombre());

				for (int j = 0; j < (resultado.getCantidadResultados()); j++) {
					
					Long idPOI = listadoPoi.get(j).getId();
					int cantResultado = 0;
					for (int m = 0; m < (poiList.size()); m++) {
						if (poiList.get(m).getId() == (idPOI)) {
							cantResultado = 1;
						}
					}
					if (cantResultado == 0) {
						poiList.add(listadoPoi.get(j));
					}
					
				}
			}

		}

	}
}