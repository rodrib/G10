package utn.dds.g10.entidades.administracion.acciones;

import utn.dds.g10.entidades.ResultadoConsulta;

public class AuditarResultadoConsulta implements Accion {
	
	ResultadoConsulta resultado; 
	Integer demoraConsulta;
	String criterio;
	
	public AuditarResultadoConsulta(ResultadoConsulta res, Integer demora, String criter)
	{
		this.resultado = res;
		this.demoraConsulta = demora;
		this.criterio =criter;
	}
	
	public AuditarResultadoConsulta() {
		// TODO Auto-generated constructor stub
	}

	public void Ejecutar() {
		resultado.setTiempoConsulta(demoraConsulta);
		resultado.setCriterioBusqueda(criterio);
		resultado.setCantidadResultados();
	}
}
