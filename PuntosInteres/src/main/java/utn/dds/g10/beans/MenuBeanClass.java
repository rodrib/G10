package utn.dds.g10.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.administracion.Usuario;

@ManagedBean(name = "menuBean", eager = true)
@javax.faces.bean.ApplicationScoped
public class MenuBeanClass {

	@ManagedProperty(value = "#{menubean}")
	private LoginBeanClass loginBean;

	public Usuario getUsuarioLogin() {

		this.message = this.loginBean.getName();
		this.usuarioLogin = DaoRelacional.obtenerUsuariosPorNombre(
				this.loginBean.getName(), Usuario.class);

		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public LoginBeanClass getMessageBean() {
		return loginBean;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private Usuario usuarioLogin;

	private String message;

	public MenuBeanClass() {

	}

	public String getMessage() {
		if (this.loginBean != null) {
			this.message = this.loginBean.getName();
			this.usuarioLogin = DaoRelacional.obtenerUsuariosPorNombre(
					this.loginBean.getName(), Usuario.class);
		}
		return message;
	}

	public void setMessageBean(LoginBeanClass loginBeanClass) {
		this.loginBean = loginBeanClass;
	}

	public String logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true)).invalidate();
		return "login";
	}
}