package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import utn.dds.g10.beans.HistorialBean;

public class fechaStringTest {
	
	@Test
	public void testearFormatoFecha() throws Exception {
		LocalDateTime fecha = HistorialBean.obtenerFecha("30/12/2012");
		System.out.println(fecha.toString());
		assertTrue("Fecha es igual 30/12/2012 ", fecha.toString()!=null );
	}
	
	
}
