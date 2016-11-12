package utn.dds.g10.test.modelo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.entidades.Elemento;
import utn.dds.g10.entidades.PruebaPersistencia;
import utn.dds.g10.modelo.ConexionDB;
import utn.dds.g10.modelo.conexion;
import utn.dds.g10.pruebaPersistencia.*;

public class conexionTest {
	
	DaoRelacional repositorio;
	
	@Before
	public void setUp()
	{
		repositorio = new DaoRelacional();
	}

	@Test
	public void PruebaCOnJDBC() throws Exception {
		conexion.main(null);
	}
	
	@Test
	public void PruebaConSessionFactory() throws Exception {
		Elemento elem = new Elemento();
		elem.setNumero(222);
		elem.setNombre("lalala");
		PruebaPersistencia prueba = new PruebaPersistencia();
		prueba.setId(1);
		prueba.setNombre("juancito");
		prueba.getLista().add(elem);
		prueba.getListaText().add("empanada");
		
		Session session = ConexionDB.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(prueba);
		System.out.println("3. Before committing save transaction");
		tx1.commit();
		PruebaPersistencia pruebaObt = new PruebaPersistencia();
		pruebaObt = (PruebaPersistencia) session.get(PruebaPersistencia.class, 1);
		Elemento elemObt = pruebaObt.getLista().get(0);
		System.out.println("nom "+pruebaObt.getNombre()+" elem "+elemObt.getNumero());
		System.out.println("text "+pruebaObt.getListaText().get(0));
		System.out.println("*****");
	}
	
	@Test
	public void testHerencia() throws Exception{
		
		Normal normal = new Normal("normal", 21, "Empleado");
	    Tecnologo tecnologo = new Tecnologo("tecnologo", 24, 4);
	    Programador programador1 = new Programador("primer programador", 25, 4, "java", 4);
	    Programador programador2 = new Programador("segundo programador", 25, 5, "java", 2);
	    Tester tester = new Tester("tester", 18, 3, "JUnit");
	    
	    Session session = ConexionDB.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(normal);
		session.save(tecnologo);
		session.save(programador1);
		session.save(programador2);
		session.save(tester);
		tx1.commit();
		session.disconnect();
	    
	    
	    
	}
	
	@Test
	public void altaConList() {
		
		Customer cus;
		Order o = new Order();
		o.setNumber("3");
		
		List<Order> lista = new ArrayList<Order>();
		lista.add(o);
		
		cus = new Customer();
		cus.setOrders(lista);

		repositorio.crearEntidad(cus);
		DaoRelacional.getSession().flush();
		
		o.setCustomer(cus);
		repositorio.crearEntidad(o);
		
	}
}
