package utn.dds.g10.test.e6;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.DaoBase;
import utn.dds.g10.entidades.CGP;
import utn.dds.g10.entidades.Coordenada;
import utn.dds.g10.entidades.Locacion;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.ServicioCGP;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.TipoPoi;

public class PersistenciaPoiTest {
	
	POI poiBanco;
	POI poiCGP;
	
	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}
	
	private void InicializarTest() {

		poiBanco = new POI();
		Coordenada coordenada = new Coordenada(1, 2);
		Locacion locacion = new Locacion();
		locacion.setCoordenada(coordenada);
		poiBanco.setLocacion(locacion);
		SucursalBanco banco = new SucursalBanco();
		banco.getServicios().add("cheques");
		poiBanco.setNombre("Banco GGG");
		poiBanco.setTipo(banco);
		
		poiCGP = new POI();
		CGP cgp = new CGP();
		ServicioCGP servicioCGP = new ServicioCGP();
		servicioCGP.setNombre("deudas");
		poiCGP.setNombre("Comuna 45");
		poiCGP.setTipo(cgp);
		servicioCGP.setCgp(cgp);
		cgp.getServicios().add(servicioCGP);
		
	}
	
	
//	Obtener   un   POI,   modificar   sus   coordenadas   geográficas,   persistirlo,   recuperarlo   y  
//	verificar que las coordenadas sean las ingresadas en la última modificación. 
	@Test
	public void modificarPoiTest()
	{
		
		DaoBase.iniciar();
		int id = DaoBase.crearEntidad(poiBanco);
		POI poiObtenido = new POI();
		poiObtenido = DaoBase.obtenerPOI(id);
		
//		TipoPoi tipo = new SucursalBanco();
//		tipo = poiObtenido.getTipo();
//
//		SucursalBanco banco = new SucursalBanco();
//		banco = (SucursalBanco) tipo.obtenerPOI(tipo.getIdTipoPoi());
//		
//		System.out.println("Servicio 1 "+banco.getServicios().get(0));
		Coordenada coordenada = poiObtenido.getLocacion().getCoordenada();
		System.out.println("Latitud "+coordenada.getLatitud()+" Longitud "+coordenada.getLongitud());
		
//		banco.getServicios().add("plazo fijo");
		Coordenada coordenadaModificada = new Coordenada(2, 3);
		Locacion locacion = new Locacion();
		locacion.setCoordenada(coordenadaModificada);
		poiObtenido.setLocacion(locacion);
//		poiObtenido.setTipo(banco);
		DaoBase.modificarEntidad(poiObtenido);
		
		DaoBase.iniciar();
		
		POI poiModificado = new POI();
		poiModificado = DaoBase.obtenerPOI(id);
		
		System.out.println("Latitud Mod"+poiModificado.getLocacion().getCoordenada().getLatitud()+" Longitud Mod"+poiModificado.getLocacion().getCoordenada().getLongitud());
		
//		TipoPoi tipoModificado = new SucursalBanco();
//		tipoModificado = (TipoPoi) poiModificado.getTipo();
//
//		TipoPoi bancoModificado = new SucursalBanco();
//		bancoModificado = tipoModificado.obtenerPOI(tipoModificado.getIdTipoPoi());
//		
//		System.out.println("Servicio 2 "+((SucursalBanco) bancoModificado).getServicios().get(1));
		
//		DaoBase.iniciar();
//		int idCGP = DaoBase.crearEntidad(poiCGP);
//		POI poiCGPObtenido = new POI();
//		poiCGPObtenido = DaoBase.obtenerPOI(idCGP);
//		
//		TipoPoi tipoCGP = new CGP();
//		tipoCGP = poiObtenido.getTipo();
//
//		CGP cgp = new CGP();
//		cgp = (CGP) tipoCGP.obtenerPOI(tipoCGP.getIdTipoPoi());
//		
//		System.out.println("Nombre CGP "+poiCGPObtenido.getNombre());
//		System.out.println("Servicio CGP "+cgp.getServicios().get(0).getNombre());
		
	}
	
//	Crear   un   nuevo   PoI,   persistirlo,   recuperarlo,   eliminarlo   y   al   solicitar   nuevamente   su  
//	recuperación, la respuesta deberá ser que no existe (null). 
	// Crea poi con id, con un id de tipo poi -> id
	@Test
	public void crearEliminarPoiTest() throws Exception
	{
		
		DaoBase.iniciar();
		int id = DaoBase.crearEntidad(poiBanco);
		POI poiObtenido = new POI();
		poiObtenido = DaoBase.obtenerPOI(id);
		
		TipoPoi tipo = new SucursalBanco();
		tipo = poiObtenido.getTipo();

		SucursalBanco banco = new SucursalBanco();
		banco = (SucursalBanco) tipo.obtenerPOI(tipo.getIdTipoPoi());
		
		System.out.println("Servicio 1 "+banco.getServicios().get(0));
		
		DaoBase.eliminarPoi(poiObtenido);
		
		POI poiEliminado = new POI();
		poiEliminado = DaoBase.obtenerPOI(id);
			
		if (poiEliminado==null)
			System.out.println("El banco fue eliminado");
		
	}
	
	

}
