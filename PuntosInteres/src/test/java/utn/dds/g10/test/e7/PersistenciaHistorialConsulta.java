package utn.dds.g10.test.e7;

import java.io.IOException;
import java.net.MalformedURLException;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import utn.dds.g10.DAO.DaoMongo;
import utn.dds.g10.entidades.Coordenada;
import utn.dds.g10.entidades.HistorialConsultas;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;

public class PersistenciaHistorialConsulta {
	DaoMongo repositorio;
	Usuario usu;
	
	@Before
	public void setUp() throws Exception {
		repositorio = new DaoMongo();
		this.InicializarTest();
	}
	
	private void InicializarTest() throws MalformedURLException, JSONException, IOException {
		
		usu = new Usuario();
		usu.setId(1);
		usu.setNombre("Guillermo");
		usu.setRol(new RolAdministrador());
		
	}
	
	@Test
	public void guardarBusquedaYMostrar() throws MalformedURLException, JSONException, IOException
	{
		ResultadoConsulta respuesta;
		GestorPoi gestor = new GestorPoi(usu);
		ResultadoConsulta resultado =  gestor.BuscarPoi("anco", usu.getNombre());
		ObjectId id =repositorio.crearEntidad(resultado);
//		respuesta = repositorio.obtenerEntidadPorId(id, ResultadoConsulta.class);
//		
//		Gson gson = new Gson();
//        String json = gson.toJson(respuesta);
//		System.out.println(json);
		
		int a;
	}
}
