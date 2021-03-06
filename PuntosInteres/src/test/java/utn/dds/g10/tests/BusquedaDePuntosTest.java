package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;
import utn.dds.g10.mappers.BancosJSON;

public class BusquedaDePuntosTest {

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
	public void ExisteAlMenosUnBancoFrances() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Banco Frances",usuario);
		assertTrue("Existe al menos un banco en la lista de Puntos de Interes", resultado.getPuntos().size() > 0 );
	}
	
//	@Test
//	public void ExistenKioscos() throws Exception {
//		ResultadoConsulta resultado = miGestor.BuscarPoi("Kiosco",usuario);
//		HistorialConsultas historial = miGestor.listadoHistorialConsultas();
////		Reportes reporte = new Reportes(historial) ;
////		reporte.imprimirReportePorFecha();
//		Assert.assertTrue("Existen kioscos", resultado.getPuntos().size() > 0);	
//	}
	
	@Test
	public void BusquedaParadaColectivo() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("114",usuario);
		Assert.assertTrue("Encontro la parada 114", resultado.getPuntos().size() > 0);		
	}
	
	///Busco un local comercial de tipo libreria.
	@Test
	public void BuscarLocalLibreria() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Libreria",usuario);
		Assert.assertTrue("Existe al menos un poi de tipo libreria", resultado.getPuntos().size() > 0);	
	}
	
	@Test 
	public void testBnacosExternos() throws MalformedURLException, JSONException, IOException
	{
		List<POI> bancosExternos =  BancosJSON.obtenerBancos();
		
		int a;
	}
	
//	@Test
//	public void BuscarPorPalabraClave() throws Exception {
//		ResultadoConsulta resultado = miGestor.BuscarPoi("Descuentos",usuario);
//		
//		Assert.assertTrue("Existe poi con la palabra clave ingresada", resultado.getPuntos().size() > 0);	
//	}
	
}
