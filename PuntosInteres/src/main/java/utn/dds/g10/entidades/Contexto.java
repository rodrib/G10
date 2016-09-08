package utn.dds.g10.entidades;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

import utn.dds.g10.gestores.GestorPoi;

public class Contexto {
	
	private Locacion locacion;
	private GestorPoi gestorPoi;
	private String usuario;
	
	public Contexto(){
		gestorPoi = new GestorPoi();
	}

	public ResultadoConsulta buscarPOI(String tag) throws MalformedURLException, JSONException, IOException {
		
		ResultadoConsulta rc = gestorPoi.BuscarPoi(tag,usuario);
		return rc;
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}

}
