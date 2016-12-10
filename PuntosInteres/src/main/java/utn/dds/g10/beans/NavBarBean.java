package utn.dds.g10.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.administracion.Usuario;

@ManagedBean(name= "navbarbean", eager = true)
@ApplicationScoped
public class NavBarBean {
	
	@ManagedProperty(value = "#{loggin}")
	private LogginBean log;
	
	private Usuario usuarioLogueado;

	public Usuario getUsuarioLogueado() {
		this.usuarioLogueado = DaoRelacional.obtenerUsuariosPorNombre(
				this.log.getName(), Usuario.class);
		
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(Usuario usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public LogginBean getLog() {
		return log;
	}

	public void setLog(LogginBean log) {
		this.log = log;
	}
	
	public NavBarBean()
	{
		
	}
	
	public String logout()
	{
		return "login";
		
	}
}
