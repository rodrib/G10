package utn.dds.g10.entidades.administracion.acciones;

import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.RolTerminal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "accion")
@JsonSubTypes({
@Type(value = AuditarResultadoConsulta.class),
@Type(value = AuditarTiempoConsulta.class)
})
public abstract class Accion  {
	public abstract void Ejecutar();
	
	private String accion;
	
	public String getaccion() {
		return accion;
	}

	public void setaccion(String accion) {
		this.accion = accion;
	}
}
