package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.GestorPoi;

public class BusquedaDePuntosTest {

	ResultadoConsulta resultadoConsulta;
	List<POI> listaPuntos;
	
	POI puntoUno;
	POI puntoDos;
	
	
	GestorPoi miGestor;

	private void InicializarTest() {

		//Inicializo Gestor
		this.miGestor = new GestorPoi();
		
		//Inicializo POI
		this.puntoUno = new POI();
		this.puntoDos = new POI();
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	@Test
	public void ExisteAlMenosUnBanco() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Santander");
		assertTrue("Existe al menos un banco en la lista de Puntos de Interes", resultado.getPuntos().size() > 0 );
	}
	
	@Test
	public void ExistenKioscos() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Kiosco");
		Assert.assertTrue("Existen kioscos", resultado.getPuntos().size() > 0);	
	}
	
	@Test
	public void BusquedaParadaColectivo() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("114");
		Assert.assertTrue("Encontro la parada 114", resultado.getPuntos().get(0).getNombre().equals("Parada 114"));		
	}
	
	///Busco un local comercial de tipo libreria.
	@Test
	public void BuscarLocalLibreria() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Libreria");
		
		Assert.assertTrue("Existe al menos un poi de tipo libreria", resultado.getPuntos().size() > 0);	
	}
	
	@Test
	public void BuscarPorPalabraClave() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Descuentos");
		
		Assert.assertTrue("Existe poi con la palabra clave ingresada", resultado.getPuntos().size() > 0);	
	}
	
}
