package utn.dds.g10.entidades;
import utn.dds.g10.gestores.GestorPoi;
public class Contexto {
	
	Locacion locacion;

		public ResultadoConsulta buscarPOI(String tag) {
		GestorPOI gp = new GestorPOI();
		ResultadoConsulta rc = new ResultadoConsulta();
		rc = gp.buscarPOI(tag);
		return rc;
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}
	
	
}
