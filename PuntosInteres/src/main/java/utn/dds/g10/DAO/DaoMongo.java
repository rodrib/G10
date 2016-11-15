package utn.dds.g10.DAO;

import java.lang.reflect.InvocationTargetException;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidadesFactory.*;
import utn.dds.g10.mappers.BsonUsuario;
import utn.dds.g10.modelo.ConexionMongoDB;

//public class DaoMongo implements Dao {
public class DaoMongo {

	public ObjectId crearEntidad(Object entidad) {
		Document doc = DocumentFactory.CrearDocumento(entidad);
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
	
	public Usuario obtenerEntidadPorId(ObjectId id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		BasicDBObject oid = new BasicDBObject("_id", id);
		MongoDatabase db = ConexionMongoDB.getMongoDataBase();
		FindIterable<Document> docs = db.getCollection("ddscollection").find(oid);
		Document doc = docs.first();
		
		return (new BsonUsuario()).getEntidad(doc);
	}
}
