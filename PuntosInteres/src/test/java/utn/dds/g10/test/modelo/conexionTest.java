package utn.dds.g10.test.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.DAO.DepartamentDao;
import utn.dds.g10.entidades.Elemento;
import utn.dds.g10.entidades.PruebaPersistencia;
import utn.dds.g10.entidades.administracion.Usuario;
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


	public void PruebaManyToMany() {

	        System.out.println("Hibernate many to many (Annotation)");
	        SessionFactory sf = ConexionDB.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();

		Stock stock = new Stock();
	        stock.setStockCode("7052");
	        stock.setStockName("PADINI");

	        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
	        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");

	        List<Category> categories = new ArrayList<Category>();
	        categories.add(category1);
	        categories.add(category2);

	        stock.setCategories(categories);

	        session.save(stock);

		session.getTransaction().commit();
		System.out.println("Done");
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
	public void testPersistenciaList()
	{
		SessionFactory sf = ConexionDB.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		//El departamento es el padre
		Department department = new Department();
		department.setDepartmentName("Ventas");
		session.save(department); // Se guarda primero el Padre
		
		Department department2 = new Department();
		department2.setDepartmentName("Compras");
		session.save(department2); // Se guarda primero el Padre
		
		
		
		Employee emp1 = new Employee("José", "Argento", "111");
		Employee emp2 = new Employee("Dardo", "Fuseneco", "222");
		
		//Listado de empleados de un departamento
		List<Employee> listaEmployees = new ArrayList<Employee>();
		listaEmployees.add(emp1);
		listaEmployees.add(emp2);

		//A cada hijo le asigno el mismo padre.
		for (Employee employee : listaEmployees) {
			employee.setDepartment(department);
		}

		//Grabo cada hijo. Uno por uno.
		for (Employee employee : listaEmployees) {
			session.save(employee);
		}
		
		//A cada hijo le asigno el mismo padre.
		for (Employee employee : listaEmployees) {
			employee.setDepartment(department2);
		}

		//Grabo cada hijo. Uno por uno.
		for (Employee employee : listaEmployees) {
			session.save(employee);
		}
				
				
				
		
		session.getTransaction().commit();
		
		session.close();
		
		//Department departmentObt = (Department) DepartamentDao.obtenerDeparmentPorId(10L, Department.class);
	//	Employee elemObt = departmentObt.getEmployees().get(0);
		//System.out.println(" elem "+elemObt.getFirstname());
		
	}
}
