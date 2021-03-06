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
//[{"comuna":3,"zonas":"San Cristobal, Balvanera","director":"","domicilio":"","telefono":"","servicios":[]},{"comuna":6,"zonas":"Caballito","director":"","domicilio":"","telefono":"","servicios":[]},{"comuna":8,"zonas":"Villa Soldati, Villa Riachuelo, Villa Lugano","director":"","domicilio":"","telefono":"","servicios":[]},{"comuna":2,"zonas":"Recoleta","director":"","domicilio":"","telefono":"","servicios":[]},{"comuna":4,"zonas":"La Boca, Barracas, Parque Patricios, Nueva Pompeya","director":"","domicilio":"","telefono":"","servicios":[]},{"comuna":11,"zonas":"Villa General Mitre, Villa Devoto, Villa del Parque, Villa Santa Rita","director":"","domicilio":"","telefono":"","servicios":[]},{"comuna":12,"zonas":"Coghlan, Saavedra, Villa Urquiza, Villa Pueyrredón","director":"","domicilio":"","telefono":"","servicios":[]},{"comuna":5,"zonas":"Almagro, Boedo","director":"","domicilio":"","telefono":"","servicios":[]},

public class DatosCGPExternos {

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
	public void BusquedaCGPNombre() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Comuna 3",usuario);
		Assert.assertTrue("Encontro el CGP por nombre", resultado.getPuntos().get(0).getNombre().equals("Comuna 3"));
		}
	
	@Test
	public void BusquedaCGPZona() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Versalles",usuario);
		Assert.assertTrue("Encontro el CGP por zona", (resultado.getPuntos().get(0).getTipo())!=null);
		}
	
	@Test
	public void BusquedaCGPNombreServicio() throws Exception {
		ResultadoConsulta resultado = miGestor.BuscarPoi("Atención ciudadana",usuario);
		Assert.assertTrue("Encontro el CGP por nombre de servicio", (resultado.getPuntos().get(0).getTipo())!=null);
		}
	
}

