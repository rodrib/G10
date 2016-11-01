package utn.dds.g10.test.e6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.DaoBase;
import utn.dds.g10.DAO.UsuariosDao;
import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.modelo.DAO;

public class PersistenciaUsuarioTest {

	Usuario usuarioAlta;
	Usuario usuarioModificacion;
	Usuario usuarioConsulta;
	Usuario usuarioEliminar;

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
	}

	private String obtenerHoraString() {
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		return hourFormat.format(date);
	}

	private void InicializarTest() {
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

		// alta
		usuarioAlta = new Usuario();
		usuarioAlta.setNombre("Usuario Alta " + obtenerHoraString());

		Rol rolUsuarioPersistible = new RolAdministrador();
		usuarioAlta.setRol(rolUsuarioPersistible);

		// modificacion
		usuarioModificacion = new Usuario();
		usuarioModificacion.setId(3);
		usuarioModificacion.setNombre("Nombre modificado "
				+ obtenerHoraString());

		// Eliminar
		usuarioEliminar = new Usuario();
		usuarioEliminar.setId(5);
	}

	@Test
	public void altaUsuario() {
		DaoBase.crearEntidad(usuarioAlta);
	}

	@Test
	public void obtenerUsuarioPorId() throws Exception {
		usuarioConsulta = UsuariosDao.obtenerUsuarioPorId(5);
		System.out.println("Nombre de usuario: " + usuarioConsulta.getNombre());
	}

	@Test
	public void modificarUsuario() {
		DaoBase.modificarEntidad(usuarioModificacion);
	}

	@Test
	public void elminarUsuario() throws Exception {
		DaoBase.eliminarEntidad(usuarioEliminar);
	}

	@Test
	public void ObtenerYModificarUsuario() throws Exception {
		usuarioConsulta = UsuariosDao.obtenerUsuarioPorId(10);
		usuarioConsulta.setNombre("obtener y modi ");
		DaoBase.modificarEntidad(usuarioConsulta);
	}

	@Test
	public void testEntrega6Usuario() throws Exception {
		// Dar de alta y persistir usuario
		int idUsuarioCreado = DaoBase.crearEntidad(usuarioAlta);

		// Recuperación de usuario
		Usuario usuarioCreado = (Usuario) DaoBase.obtenerEntidadPorId(idUsuarioCreado, Usuario.class);

		// Modificación de Usuario y persistir modificación
		usuarioCreado.setNombre("nombre modificado: " + obtenerHoraString());
		DaoBase.modificarEntidad(usuarioCreado);

		// Recupero usuario luego de modifiación
		Usuario usuarioLuegoDeModificacion = (Usuario) DaoBase.obtenerEntidadPorId(idUsuarioCreado, Usuario.class);
		
		//Comparo el nombre del usuario antes y despues.
		
		Boolean esModificado = (usuarioCreado.getNombre() != usuarioLuegoDeModificacion.getNombre());
		Assert.assertTrue("El nombre del usuario antes y depués de la modificación es igual.", esModificado);
	}
}