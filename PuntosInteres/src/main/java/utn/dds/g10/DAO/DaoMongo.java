package utn.dds.g10.DAO;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import utn.dds.g10.entidadesFactory.*;
import utn.dds.g10.modelo.ConexionMongoDB;

public class DaoMongo implements Dao {

	@Override
	public  int crearEntidad(Object entidad) {
		Document doc = DocumentFactory.CrearDocumento(entidad).getDocument();
		MongoDatabase db = ConexionMongoDB.getMongoDataBase();
		db.getCollection("ddscollection").insertOne(doc);
		return 0;
	}
}
