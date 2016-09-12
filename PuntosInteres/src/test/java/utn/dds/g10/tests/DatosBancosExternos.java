package utn.dds.g10.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;

//Lo que se encuentra en la API: 
//[{"banco":"Galicia","x":9999.0,"y":9999.0,"sucursal":"Flores","gerente":"Roberto Gomez","servicios":["Plazo Fijo","Acciones","Cobros","Pagos"]},{"banco":"Santander Rio","x":1234.0,"y":1234.0,"sucursal":"Almagro","gerente":"Lorenzo","servicios":["Moneda extranjera","Cobros","Pagos"]}]

public class DatosBancosExternos {
	
	ResultadoConsulta resultadoConsulta;
	List<POI> listaPuntos;
	
	POI puntoUno;
	POI puntoDos;
	String usuario;
	Usuario usuarioLogueado;
	
	GestorPoi miGestor;

	private void InicializarTest() {

		usuarioLogueado = new Usuario();
		RolAdministrador rol = new RolAdministrador();
		usuarioLogueado.setRol(rol);
		
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
	public void BusquedaBancoPorNombre() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Banco ICBC",usuario);
		Assert.assertTrue("Encontro el Banco por Nombre", resultado.getPuntos().size() > 0);
	}
	
	@Test
	public void BusquedaBancoPorServicio() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Cobros",usuario);
		Assert.assertTrue("Encontro el Banco por Nombre de Servicio", resultado.getPuntos().get(0)!=null);
	}
}


