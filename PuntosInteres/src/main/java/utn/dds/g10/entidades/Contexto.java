package utn.dds.g10.entidades;

public class Contexto {
	
	Locacion locacion;

	public ResultadoConsulta BuscarPoiSegunCriterio(String criterio) {
		return new ResultadoConsulta();
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}
	
	
}
