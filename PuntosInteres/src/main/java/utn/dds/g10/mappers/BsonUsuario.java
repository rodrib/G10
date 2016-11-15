package utn.dds.g10.mappers;
import java.lang.reflect.InvocationTargetException;
import org.bson.Document;
import utn.dds.g10.entidades.administracion.Usuario;

public class BsonUsuario {
	
	public Document getDocument(Usuario usuario)
	{
		Document doc = new Document();
		
		doc.append("nombre", usuario.getNombre());
		doc.append("id", usuario.getId());
		doc.append("rol", (new BsonRol()).getDocument(usuario.getRol()));
		
		return doc;
	}
	
	public Usuario getEntidad(Document doc) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		Usuario us = new Usuario();
		us.setNombre(doc.get("nombre").toString());
		us.setId((Integer)doc.get("id"));
		us.setRol((new BsonRol()).getEntidad((Document)doc.get("rol")));
		
		return us;
	}
}