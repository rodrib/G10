package utn.dds.g10.beans;

import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.administracion.Usuario;

@ManagedBean(name= "navbarbean", eager = true)
@ApplicationScoped
public class NavBarBean {
	
	@ManagedProperty(value = "#{loggin}")
	private LogginBean log;
	
	private Usuario usuarioLogueado;

	public Usuario getUsuarioLogueado() {
		//String nombreUsu = this.log.getName();
		//String a = Faces.evaluateExpressionGet("#{userManager.loggedIN}");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String nombreUsuario = (String)sessionMap.get("nombre_usuario");
		
		System.out.print("el nombre del usuario es:" + nombreUsuario.toString());
		
		this.usuarioLogueado = DaoRelacional.obtenerUsuariosPorNombre(nombreUsuario, Usuario.class);
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
