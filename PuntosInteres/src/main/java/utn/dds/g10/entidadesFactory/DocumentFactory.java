package utn.dds.g10.entidadesFactory;

import org.bson.Document;

import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.mappers.BsonUsuario;

public class DocumentFactory {
	
	static Document doc;
	
	public static Document CrearDocumento(Object o)
	{
		if(o.getClass().getName() == Usuario.class.getName())
		{
			//doc =  new DocumentoJsonUsuario(o);
			doc = (new BsonUsuario()).getDocument((Usuario)o);
		}
		
		return doc;
	}

}
