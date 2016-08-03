package utn.dds.g10.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.GestorPoi;

public class DatosCGPExternos {

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
	public void BusquedaCGP() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("CGP");
		Assert.assertTrue("Encontro el CGP", resultado.getPuntos().get(0).getNombre().equals("CGP"));	

	}
	
}

