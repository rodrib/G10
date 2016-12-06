package utn.dds.g10.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.administracion.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@FacesValidator("utn.dds.g10.validadores.ValidadorLogin")
public class ValidadorLogin implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
			
		String usuarioIngresado = value.toString();
	
		//String pass;// = (String) component.getAttributes().get("atrPassword");
		
		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("atrPassword");
	    String pass = uiInputConfirmPassword.getSubmittedValue().toString();
			  
	    Usuario usuario = new Usuario();
	    try {
	    	usuario = DaoRelacional.obtenerUsuariosPorNombre(usuarioIngresado, Usuario.class);		
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Error de conexión.", "Ocurrió un error al intentar conectarse a la base de datos.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);	
		}
		
		
		if(!usuarioIngresado.equals(usuario.getNombre()) || !pass.equals(usuario.getPassword()))
		{
			FacesMessage msg = new FacesMessage("Usuario y/o password incorrecto.", "El usuario no existe o la password es incorrecta");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);	
		}
	}
}