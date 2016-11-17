package utn.dds.g10.test.e7;

import java.io.IOException;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import utn.dds.g10.DAO.DaoMongo;
import utn.dds.g10.entidades.Coordenada;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.entidadesFactory.DocumentFactory;
import utn.dds.g10.mappers.BsonUsuario;
import utn.dds.g10.modelo.ConexionMongoDB;

public class PersistenciaPoiTest {
	DaoMongo repositorio;
	POI poitest;
	
	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
		repositorio = new DaoMongo();
	}
	
	private void InicializarTest() {
		
		poitest = new POI();
		poitest.setNombre("Banco Nacion Test");
		poitest.setId(1);
		
		Locacion locaciontest = new Locacion();
		locaciontest.setBarrio("las lomitas");
		locaciontest.setCallePrincipal("Almirante Brown");
		locaciontest.setCodigoPostal(1832);
		locaciontest.setCoordenada(new Coordenada(12, 20));
		locaciontest.setPais("Argentina");
		poitest.setLocacion(locaciontest);
	}

	@Test
	public void testGuardarPoi()
	{
		repositorio.crearEntidad(poitest);
	}
	
	@Test
	public void testGetJsonFromDbPorId() throws JsonParseException, JsonMappingException, IOException
	{
		ObjectId id = repositorio.crearEntidad(poitest);
		POI poiDB =  repositorio.obtenerEntidadPorId(id, POI.class);
		
		System.out.println(poiDB.getNombre());
	}

}
