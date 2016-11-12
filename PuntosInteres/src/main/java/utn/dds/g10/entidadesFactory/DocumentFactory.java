package utn.dds.g10.entidadesFactory;

import org.bson.Document;

import utn.dds.g10.entidades.administracion.Usuario;

public class DocumentFactory {
	
	static DocumentoJson doc;
	
	public static DocumentoJson CrearDocumento(Object o)
	{
		if(o.getClass().getName() == Usuario.class.getName())
		{
			doc =  new DocumentoJsonUsuario(o);
		}
		
		return doc;
	}

}
