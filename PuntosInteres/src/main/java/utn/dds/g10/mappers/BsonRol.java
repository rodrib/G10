package utn.dds.g10.mappers;

import java.lang.reflect.InvocationTargetException;

import org.bson.Document;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.RolTerminal;
import utn.dds.g10.entidades.administracion.acciones.Accion;

public class BsonRol {
	public Document getDocument(Rol rol) {
		Document doc = new Document();
		doc.append("id", rol.getIdRol());
		doc.append("nombre", rol.getClass().getSimpleName());
		BasicDBList acciones = new BasicDBList();

		for (Accion acc : rol.getAcciones()) {
			acciones.add(new BasicDBObject("accion", acc.getClass().getSimpleName()));
		}

		doc.append("acciones", acciones);

		return doc;
	}
	
	public Rol getEntidad(Document doc) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		Rol rol = null;
	
		if(doc.getString("nombre").equals(RolAdministrador.class.getSimpleName()))
		{
			rol=  new RolAdministrador();
		}
		else if(doc.getString("nombre").equals(RolTerminal.class.getSimpleName()))
		{
			rol =  new RolTerminal();
		}
		
		return rol;
	}

}
