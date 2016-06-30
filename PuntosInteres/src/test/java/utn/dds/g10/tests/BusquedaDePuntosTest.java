package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.gestores.GestorPoi;

public class BusquedaDePuntosTest {

	ResultadoConsulta resultadoConsulta;
	List<POI> listaPuntos;

	GestorPoi miGestor;

	private void InicializarTest() {

		//Inicializo Gestor
		this.miGestor = new GestorPoi();
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	@Test
	public void ExisteAlMenosUnBanco() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Banco");
		assertTrue("Existe al menos un banco en la lista de Puntos de Interes", resultado.getPuntos().size() > 0 );
	}
}
