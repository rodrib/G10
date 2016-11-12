package utn.dds.g10.modelo;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConexionMongoDB {
	
@SuppressWarnings("resource")
public  static MongoDatabase getMongoDataBase()
{
	MongoDatabase db = null;
	MongoClient mClient;
	
	try {
		mClient = new MongoClient();
		 db =  mClient.getDatabase("test"); 
	} catch (Exception e) {
		System.out.print("No se pudo conectar a la base mongo: " + e.getMessage());
	}	
	
	return db;
}

}
