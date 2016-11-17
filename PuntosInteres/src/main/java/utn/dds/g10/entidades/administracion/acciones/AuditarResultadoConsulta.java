package utn.dds.g10.entidades.administracion.acciones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import utn.dds.g10.entidades.ResultadoConsulta;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AuditarResultadoConsulta extends Accion {
	
	ResultadoConsulta resultado; 
	Integer demoraConsulta;
	String criterio;
	
	public AuditarResultadoConsulta(ResultadoConsulta res, Integer demora, String criter)
	{
		this.resultado = res;
		this.demoraConsulta = demora;
		this.criterio =criter;
		this.setaccion(this.getClass().getSimpleName());
	}
	
	public AuditarResultadoConsulta() {
		// TODO Auto-generated constructor stub
		this.setaccion(this.getClass().getSimpleName());
	}

	public void Ejecutar() {
		resultado.setTiempoConsulta(demoraConsulta);
		resultado.setCriterioBusqueda(criterio);
		resultado.setCantidadResultados();
	}
}
