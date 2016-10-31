package utn.dds.g10.DAO;

import utn.dds.g10.entidades.administracion.Usuario;

public class UsuariosDao extends DaoBase {

	public static Usuario obtenerUsuarioPorId(int idUsuario) {
		try {
			iniciar();
			Usuario usuarioDB = (Usuario) session.get(Usuario.class, idUsuario);
			confirmarYTerminar();
			return usuarioDB;
		} catch (Exception e) {
			return null;
		}
	}
}
