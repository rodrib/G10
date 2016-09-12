package utn.dds.g10.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.datos.Repositorio;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ParadaColectivo;
import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;

public class AMBCPoiTest {

	ResultadoConsulta resultadoConsulta;
	List<POI> listaPuntos;
	
	POI puntoUno;
	POI puntoDos;
	
	Usuario usuarioLogueado;
	
	
	GestorPoi miGestor;

	private void InicializarTest() {
		
		usuarioLogueado = new Usuario();
		
		RolAdministrador roladmin = new RolAdministrador();
		
		usuarioLogueado.setRol(roladmin);
		
		this.miGestor = new GestorPoi(usuarioLogueado);
		this.puntoUno = new POI();
		this.puntoDos = new POI();
		
		this.puntoUno.setLocacion(new Locacion());
		this.puntoUno.setNombre("Parada53");
		this.puntoUno.setTipo(new ParadaColectivo());
	
		puntoDos.setLocacion(new Locacion());
		puntoDos.setNombre("Parada114");
		puntoDos.setTipo(new ParadaColectivo());
	}

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	@Test
	public void AltaPOI() throws Exception {
		
		int cantidadAntes = Repositorio.getInstance().getDatos().size();
		Repositorio.AgregarPOI(puntoUno);
		int cantidadDespues = Repositorio.getInstance().getDatos().size();
		
		assertTrue("Se agregó un poi correctamente", cantidadAntes < cantidadDespues);		
	}
	
	@Test
	public void BajaPOI() throws Exception {
		
		Repositorio.AgregarPOI(puntoDos);
		int cantidadAntes = Repositorio.getInstance().getDatos().size();
		
		Repositorio.EliminarPOI(puntoDos);
		int cantidadDespues = Repositorio.getInstance().getDatos().size();
		
		assertTrue("Se eliminó un poi correctamente", cantidadAntes > cantidadDespues);		
	}
	
	@Test
	public void ModificarNombrePOI() throws Exception {
		
		int indice  = 2;
		String nombreAnterior;
		String nombreActual = "Banco CITI";
		String nombrePosterior;
		
		POI poi = Repositorio.getInstance().getDatos().remove(indice);
		nombreAnterior = poi.getNombre();
		poi.setNombre(nombreActual);
		
		Repositorio.getInstance().getDatos().add(indice, poi);
		
		poi = Repositorio.getInstance().getDatos().get(indice);
		nombrePosterior = poi.getNombre();
		
		assertTrue("Se modificó el nombre del poi correctamente", nombreAnterior != nombrePosterior);
	}
}
	
	
