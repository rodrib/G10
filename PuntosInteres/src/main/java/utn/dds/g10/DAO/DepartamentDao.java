package utn.dds.g10.DAO;

import utn.dds.g10.DAO.DaoRelacional;
import utn.dds.g10.pruebaPersistencia.Department;

public class DepartamentDao extends DaoRelacional {

	public static Department obtenerDeparmentPorId(Long idDepartament) throws Exception {
		try {
			Department departamentoDB = (Department)DaoRelacional.obtenerDeparmentPorId(idDepartament, Department.class);
			return departamentoDB;
		} catch (Exception e) {
			throw new Exception("Ocurrió un error al intentar obtener el usuario");
		}
	}
}