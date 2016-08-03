package utn.dds.g10.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.GestorPoi;

public class DatosBancosExternos {
	
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
	public void BusquedaBanco() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("114");
		Assert.assertTrue("Encontro el Banco", resultado.getPuntos().get(0).getNombre().equals("Banco"));	

	}
	
}


