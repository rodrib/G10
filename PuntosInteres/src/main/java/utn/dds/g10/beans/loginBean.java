package utn.dds.g10.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

//@ManagedBean(name="user")
@SessionScoped
public class loginBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void login_action()
	{
		//this.name
	}
}
