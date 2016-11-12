package utn.dds.g10.test.e6;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.DAO.ResultadoConsultaDAO;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.TestEntidad;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;
import utn.dds.g10.modelo.ConexionDB;

public class PersistenciaBusquedaTest {

	DaoRelacional repositorio;
	
	// Realizar una búsqueda, persistirla, recuperarla y verificar que
	// corresponda al objeto  de esa búsqueda e incluya referencias a los PoI.
	void persistirBusquedaTest() {

	}
	
	@Before 
	public void setUp()
	{
		repositorio = new DaoRelacional();
	}

	@Test
	public void persistirResultadoBúsqueda() throws MalformedURLException,
			JSONException, IOException {

		Usuario user = new Usuario();
		user.setNombre("Guillermo");
		user.setRol(new RolAdministrador());

		GestorPoi gestor = new GestorPoi(user);
		ResultadoConsulta resultado = gestor.BuscarPoi("11", user.getNombre());
		
		TestEntidad testPrueba = new TestEntidad();
		testPrueba.setNombre("test");
		
		//Persisto la búsqueda
		int idResultadoNuevo = repositorio.crearEntidad(resultado);
		
		// Recuperación de resultado
		ResultadoConsulta resultadoObtenido = ResultadoConsultaDAO.obtenerEntidadPorId(idResultadoNuevo, ResultadoConsulta.class);
		List<TestEntidad> lista = new ArrayList<TestEntidad>();
		lista.add(testPrueba);
		resultadoObtenido.setArray(lista);
		
//		testPrueba.setResultadoConsulta(resultadoObtenido);
//		DaoBase.crearEntidad(testPrueba);
		//DaoBase.modificarEntidad(resultadoObtenido);
		
		int a_esperar_debug;
		//Aca se verifica que corresponde al objecto.
	}
}
