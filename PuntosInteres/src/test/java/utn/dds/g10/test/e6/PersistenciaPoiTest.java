package utn.dds.g10.test.e6;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.SucursalBanco;
import utn.dds.g10.entidades.TipoPoi;

public class PersistenciaPoiTest {
	
	POI poi;
	DaoRelacional repositorio;
	
	@Before
	public void setUp() throws Exception {
		repositorio = new DaoRelacional();
		this.InicializarTest();
	}
	
	private void InicializarTest() {

		poi = new POI();
		SucursalBanco banco = new SucursalBanco();
		banco.getServicios().add("cheques");
		poi.setNombre("Banco GGG");
		poi.setTipo(banco);
		
		
	}
	
	
//	Obtener   un   POI,   modificar   sus   coordenadas   geográficas,   persistirlo,   recuperarlo   y  
//	verificar que las coordenadas sean las ingresadas en la última modificación. 
	@Test
	public void modificarPoiTest()
	{
		
		
	}
	
//	Crear   un   nuevo   PoI,   persistirlo,   recuperarlo,   eliminarlo   y   al   solicitar   nuevamente   su  
//	recuperación, la respuesta deberá ser que no existe (null). 
	// Crea poi con id, con un id de tipo poi -> id
	@Test
	public void crearEliminarPoiTest()
	{
		
		DaoRelacional.iniciar();
		int id = repositorio.crearEntidad(poi);
		ArrayList<POI> lista = new ArrayList<POI>();
		lista = (ArrayList<POI>) DaoRelacional.obtenerPois();
		POI poiObtenido = new POI();
		for (POI p : lista) {
			if (p.getId()==id)
				poiObtenido=p;
		}
		
		System.out.println("Nombre del banco " + poiObtenido.getNombre());
		TipoPoi tipo = new SucursalBanco();
		tipo = (TipoPoi) poiObtenido.getTipo();

		System.out.println(tipo.getClass());

		TipoPoi banco = new SucursalBanco();
		banco = tipo.obtenerPOI(tipo.getIdTipoPoi());
		
		System.out.println("Servicio "+((SucursalBanco) banco).getServicios().get(0));
		
//		EntityManager entityManager; 
//		
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		
//		POI foo = new POI();
//		foo.setId(1);
//		foo.setNombre("the foo");
//		int id2 = DaoBase.crearEntidad(foo);
//		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<POI> q = qb.createQuery(POI.class);
//		Root<POI> root = q.from(POI.class);
//		q.where(qb.equal(root.get("name"), "the foo"));
//		entityManager.createQuery(q).getSingleResult();
		
		
		
	}
	
	

}
