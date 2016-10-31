package utn.dds.g10.test.e6;

import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.DaoBase;
import utn.dds.g10.DAO.UsuariosDao;
import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;

public class PersistenciaUsuarioTest {

	static Usuario usuarioPersistible;
	Usuario usuarioDB;

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	private void InicializarTest() {
		usuarioPersistible = new Usuario();
		usuarioPersistible.setNombre("UsuarioPrueba");
		usuarioPersistible.setId(3);
		Rol rolUsuarioPersistible = new RolAdministrador();
		usuarioPersistible.setRol(rolUsuarioPersistible);
	}

	// Dar de alta un usuario, persistirlo, recuperarlo, realizar una
	// modificación en el
	// nombre de usuario, recuperarlo y verificar que el cambio esté presente.
	@Test
	public void altaModificacionUsuarioTest() {
		// session.delete(usuarioPersistible);
		DaoBase.crearEntidad(usuarioPersistible);
	}

	@Test
	public void obtenerUsuarioPorId() {
	    Usuario user = UsuariosDao.obtenerUsuarioPorId(5);
	    System.out.println("Nombre de usuario: " + user.getNombre());
	}
	
	public void testUsuario()
	{
		
	}
}