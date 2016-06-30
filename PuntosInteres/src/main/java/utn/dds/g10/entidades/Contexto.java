package utn.dds.g10.entidades;

import utn.dds.g10.gestores.GestorPoi;

public class Contexto {

	Locacion locacion;

	public ResultadoConsulta buscarPOI(String tag) {
		GestorPoi gp = new GestorPoi();
		ResultadoConsulta rc = gp.BuscarPoi(tag);
		return rc;
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}

}
