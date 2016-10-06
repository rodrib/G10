package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;
import utn.dds.g10.gestores.GestorProcesos;
import utn.dds.g10.gestores.Buscador.BuscadorLocalComercial;

public class ProcesoActualizadorLocalesTest {

	ResultadoConsulta resultadoConsulta;
	List<POI> listaPuntos;
	
	POI puntoUno;
	POI puntoDos;
	String usuario;
	
	Usuario usuarioLogueado;
	
	
	GestorPoi miGestor;

	private void InicializarTest() {
		
		usuarioLogueado = new Usuario();
		RolAdministrador rolAdministrador = new RolAdministrador();
		usuarioLogueado.setRol(rolAdministrador);
		
		//Inicializo Gestor
		this.miGestor = new GestorPoi(usuarioLogueado);
		
		//Inicializo POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
		
		usuario = "userTest";
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}
	
	
	@Test
	public void testearProcesoActualizadorLocales() throws Exception {
		
		GestorProcesos gestorP = new GestorProcesos();
		
		gestorP.Ejecutar();
		BuscadorLocalComercial buscador = new BuscadorLocalComercial();
		System.out.println("Busca Carrousel");
		ResultadoConsulta resultado = buscador.BuscarLocalComercial("Carrousel");
		System.out.println(resultado.getPuntos().size());
		
		assertTrue("Existe el Local Comercial Carrousel", resultado.getPuntos().get(0).getNombre().equals("Carrousel"));
		}
	
//	@Test
//	public void testearProcesoActualizadorLocales2() throws Exception {
//		
//		GestorProcesos gestorP = new GestorProcesos();
//		
//		gestorP.Ejecutar();
//		
//		ResultadoConsulta resultado = miGestor.BuscarPoi("Kiosko MO",usuario);
//		assertTrue("Existe el Local Comercial Kiosko MO con 2 palabras claves", resultado.getPuntos().get(0).getPalabrasClaves().size() == 2);
//		}
}

