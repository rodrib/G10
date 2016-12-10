package utn.dds.g10.beans;


import javax.faces.bean.ManagedBean;

@ManagedBean(name = "loginbean", eager = true)
@javax.faces.bean.ApplicationScoped
public class LoginBeanClass {
	
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
}
