package utn.dds.g10.entidades.administracion;

import utn.dds.g10.entidades.ResultadoConsulta;

public interface  Rol {
	
	public void AuditarTiempoConsulta(int segundos);
	public void AuditarResultadoConsulta(ResultadoConsulta resultado, int demoraConsulta, String Consulta);
	
}
