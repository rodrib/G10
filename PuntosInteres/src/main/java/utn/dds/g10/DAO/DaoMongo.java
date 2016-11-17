package utn.dds.g10.DAO;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidadesFactory.*;
import utn.dds.g10.mappers.BsonUsuario;
import utn.dds.g10.modelo.ConexionMongoDB;

//public class DaoMongo implements Dao {
public class DaoMongo {
	
	
	

	public ObjectId crearEntidad(Object entidad) {
		Gson gson = new Gson();
        String json = gson.toJson(entidad);
		Document doc =  new Document();
		doc =  Document.parse(json);
		MongoDatabase db = ConexionMongoDB.getMongoDataBase();
		db.getCollection("ddscollection").insertOne(doc);
		ObjectId idObj = doc.getObjectId("_id");
		return idObj;
	}

	public void modificarEntidad(Object entidadModificada, ObjectId id) {
		MongoDatabase db = ConexionMongoDB.getMongoDataBase();
		Document filter = new Document("_id", id);
		Document entidadPersistible = DocumentFactory.CrearDocumento(entidadModificada);
		db.getCollection("ddscollection").findOneAndReplace(filter, entidadPersistible);

	}

	public void eliminarEntidad(Object entidadEliminar) throws Exception {

	}
	
	public <T> T obtenerEntidadPorId(ObjectId id, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException 
	{	
		BasicDBObject oid = new BasicDBObject("_id", id);

		MongoDatabase db = ConexionMongoDB.getMongoDataBase();
		FindIterable<Document> tDocumentList = db.getCollection("ddscollection").find(oid);
		Document doc = tDocumentList.first();
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println(doc.toJson());
	 
		Gson gson = new Gson();
        String json = gson.toJson(mapper.readValue(doc.toJson(), valueType));
		
		System.out.println(json);
		
		return mapper.readValue(doc.toJson(), valueType);
	}
}
