package utn.dds.g10.DAO;

import utn.dds.g10.entidades.administracion.Usuario;

public class UsuariosDao extends DaoRelacional {

	public static Usuario obtenerUsuarioPorId(int idUsuario) throws Exception {
		try {
			Usuario usuarioDB = (Usuario)DaoRelacional.obtenerEntidadPorId(idUsuario, Usuario.class);
			return usuarioDB;
		} catch (Exception e) {
			throw new Exception("Ocurrió un error al intentar obtener el usuario");
		}
	}
}
