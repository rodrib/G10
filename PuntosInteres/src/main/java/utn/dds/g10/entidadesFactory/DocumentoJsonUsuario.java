package utn.dds.g10.entidadesFactory;

import org.bson.Document;

import utn.dds.g10.entidades.administracion.Usuario;

public class DocumentoJsonUsuario implements DocumentoJson {

	Document doc = new Document();
	
	public DocumentoJsonUsuario(Object o) {
		Usuario usuario = (Usuario)o;
		doc.append("nombre", usuario.getNombre());
		doc.append("Id", usuario.getId());
		//doc.append("nombre", usuario.getNombre());
	}

	@Override
	public Document getDocument() {
		return doc;
	}

}
