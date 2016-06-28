package utn.dds.g10.entidades;
import java.time.LocalDate;
public class Contexto {
	
	Locacion locacion;

	public ResultadoConsulta BuscarPoiSegunCriterio(String criterio) {
		GestorPOI gp = new GestorPOI();
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
