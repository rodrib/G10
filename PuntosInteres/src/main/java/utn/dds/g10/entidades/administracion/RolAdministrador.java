package utn.dds.g10.entidades.administracion;

import java.util.ArrayList;

import utn.dds.g10.entidades.administracion.acciones.Accion;
import utn.dds.g10.entidades.administracion.acciones.AuditarResultadoConsulta;

public class RolAdministrador extends Rol {
	
	public RolAdministrador()
	{
		this.setAcciones();
	}

	@Override
	public void setAcciones() {
		this.acciones  = new ArrayList<Accion>();
		this.acciones.add(new AuditarResultadoConsulta());
	}
}
