package utn.dds.g10.DAO;

import org.hibernate.Session;

import utn.dds.g10.entidades.ResultadoConsulta;
import utn.dds.g10.modelo.ConexionDB;

public class ResultadoConsultaDAO extends DaoRelacional {
	
	public static ResultadoConsulta obtenerEntidadPorId(int idEntidad, Class<ResultadoConsulta> clase) {
		Session asession = ConexionDB.getSessionFactory().openSession();
		ResultadoConsulta entidad = (ResultadoConsulta) asession.get(clase, idEntidad);
		asession.close();
		return entidad;
	}
}
