package utn.dds.g10.entidades.administracion;

import utn.dds.g10.entidades.ResultadoConsulta;

public class RolTerminal implements Rol {

	public void AuditarTiempoConsulta(int segundos) {
		// TODO Auto-generated method stub
		
	}

	public void AuditarResultadoConsulta(ResultadoConsulta resultado, int demoraConsulta, String criterio) {
		resultado.setTiempoConsulta(demoraConsulta);
		resultado.setCriterioBusqueda(criterio);
		resultado.setCantidadResultados();
	}
	
}
