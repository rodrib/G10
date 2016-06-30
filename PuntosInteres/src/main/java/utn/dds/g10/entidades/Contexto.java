package utn.dds.g10.entidades;
import utn.dds.g10.gestores.GestorPoi;
public class Contexto {
	
	Locacion locacion;

	public ResultadoConsulta BuscarPoiSegunCriterio(String criterio) {
		GestorPoi gp = new GestorPoi();
		ResultadoConsulta rc = new ResultadoConsulta();
		rc = gp.ObtenerPoiPorCriterio(criterio);
		return rc;
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}
	
	
}
