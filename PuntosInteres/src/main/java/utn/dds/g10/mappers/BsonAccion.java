package utn.dds.g10.mappers;

import org.bson.Document;

import utn.dds.g10.entidades.administracion.acciones.Accion;

public class BsonAccion {

	public Document getDocument(Accion accion)
		{
			Document doc = new Document();
			doc.append("id", accion.getClass().getName());
			return doc;
		}
}
