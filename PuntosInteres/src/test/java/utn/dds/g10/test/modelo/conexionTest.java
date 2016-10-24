package utn.dds.g10.test.modelo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.modelo.conexion;

public class conexionTest {
	
	@Test
	public void ExisteAlMenosUnBancoFrances() throws Exception {
		//ResultadoConsulta resultado = miGestor.BuscarPoi("Banco Frances",usuario);
		conexion.main(null);
		//assertTrue("Existe al menos un banco en la lista de Puntos de Interes", resultado.getPuntos().size() > 0 );
	}

}
