package utn.dds.g10.test.e6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.dds.g10.DAO.Dao;
import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.DAO.UsuariosDao;
import utn.dds.g10.entidades.administracion.Rol;
import utn.dds.g10.entidades.administracion.RolAdministrador;
import utn.dds.g10.entidades.administracion.Usuario;
import utn.dds.g10.pruebaPersistencia.Order;

public class PersistenciaUsuarioTest {

	Usuario usuarioAlta;
	Usuario usuarioModificacion;
	Usuario usuarioConsulta;
	Usuario usuarioEliminar;
	
	Dao repositorio;
	public static int id = 13;

	@Before
	public void setUp() throws Exception {
		this.InicializarTest();
		repositorio = new DaoRelacional();
		//repositorio = new DaoMongo();
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
		usuarioAlta.setNombre("admin");
		usuarioAlta.setPassword("admin");

		Rol rolUsuarioPersistible = new RolAdministrador();
		usuarioAlta.setRol(rolUsuarioPersistible);

		// modificacion
		usuarioModificacion = new Usuario();
		usuarioModificacion.setId(id);
		usuarioModificacion.setNombre("Nombre modificado " + obtenerHoraString());

		// Eliminar
		usuarioEliminar = new Usuario();
		usuarioEliminar.setId(id);
	}

	@Test
	public void altaUsuario() {
		repositorio.crearEntidad(usuarioAlta);
	}
	
	@Test
	public void altaCliente() {
		//Order o = new Order();
		//o.setNumber("5");
		//repositorio.crearEntidad(o);
	}

	@Test
	public void obtenerUsuarioPorId() throws Exception {
		usuarioConsulta = UsuariosDao.obtenerUsuarioPorId(id);
		System.out.println("Nombre de usuario: " + usuarioConsulta.getNombre());
	}

	@Test
	public void modificarUsuario() {
		repositorio.modificarEntidad(usuarioModificacion);
	}

	@Test
	public void ObtenerYModificarUsuario() throws Exception {
		usuarioConsulta = UsuariosDao.obtenerUsuarioPorId(id);
		usuarioConsulta.setNombre("obtener y modi ");
		repositorio.modificarEntidad(usuarioConsulta);
	}
	
	@Test
	public void elminarUsuario() throws Exception {
		repositorio.eliminarEntidad(usuarioEliminar);
	}

	@Test
	public void testEntrega6Usuario() throws Exception {
		// Dar de alta y persistir usuario
		int idUsuarioCreado = repositorio.crearEntidad(usuarioAlta);

		// Recuperación de usuario
		Usuario usuarioCreado = (Usuario) DaoRelacional.obtenerEntidadPorId(idUsuarioCreado, Usuario.class);

		// Modificación de Usuario y persistir modificación
		usuarioCreado.setNombre("nombre modificado: " + obtenerHoraString());
		repositorio.modificarEntidad(usuarioCreado);

		// Recupero usuario luego de modifiación
		Usuario usuarioLuegoDeModificacion = (Usuario) DaoRelacional.obtenerEntidadPorId(idUsuarioCreado, Usuario.class);
		
		//Comparo el nombre del usuario antes y despues.
		
		Boolean esModificado = (usuarioCreado.getNombre() != usuarioLuegoDeModificacion.getNombre());
		Assert.assertTrue("El nombre del usuario antes y depués de la modificación es igual.", esModificado);
	}
}