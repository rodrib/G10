package utn.dds.g10.test.modelo;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
		prueba.setNombre("juancito");
		
		Session session = DAO.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(prueba);
		System.out.println("3. Before committing save transaction");
		tx1.commit();
		System.out.println("4. After committing save transaction");
		System.out.println("*****");
	}
}