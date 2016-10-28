package utn.dds.g10.test.modelo;
import org.hibernate.Session;
import org.junit.Test;
import utn.dds.g10.entidades.PruebaPersistencia;
import utn.dds.g10.modelo.DAO;
import utn.dds.g10.modelo.conexion;
	
public class conexionTest {
	
	@Test
	public void PruebaCOnJDBC() throws Exception {
		conexion.main(null);
	}
	
	@Test
	public void PruebaConSessionFactory() throws Exception {
		PruebaPersistencia prueba = new PruebaPersistencia();
		
		prueba.setId(1);
		prueba.setNombre("pepito");

		Session session = DAO.getSessionFactory().openSession();
		long id = (Long)session.save(prueba);
		System.out.println(" id="+id);
		session.flush();
	}
}
