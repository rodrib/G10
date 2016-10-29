package utn.dds.g10.test.e6;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.entidades.POI;
import utn.dds.g10.entidades.PruebaPersistencia;
import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.gestores.GestorPoi;
import utn.dds.g10.modelo.DAO;

public class PersistenciaUsuarioTest {

	Usuario usuarioPersistible;
	
	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}
	
	private void InicializarTest() {
		usuarioPersistible = new Usuario();
		usuarioPersistible.setNombre("Guillermo");
		
		Rol rolUsuarioPersistible = new RolAdministrador();
		
		usuarioPersistible.setRol(rolUsuarioPersistible);
	}

	// Dar de alta un usuario, persistirlo, recuperarlo, realizar una
	// modificación en el
	// nombre de usuario, recuperarlo y verificar que el cambio esté presente.
	@Test
	public void altaModificacionUsuarioTest() {
		Session session = DAO.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(usuarioPersistible);
		tx1.commit();
		session.disconnect();
	}
}
