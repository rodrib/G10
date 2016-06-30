package utn.dds.g10.entidades;

import utn.dds.g10.gestores.GestorPoi;

public class Contexto {
	
	Locacion locacion;
	GestorPoi gestorPoi;

	public ResultadoConsulta buscarPOI(String tag) {
		gestorPoi = new GestorPoi();
		ResultadoConsulta rc = gestorPoi.BuscarPoi(tag);
		return rc;
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}

}
