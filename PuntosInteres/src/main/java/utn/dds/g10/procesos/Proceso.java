package utn.dds.g10.procesos;

import org.quartz.JobDetail;

public interface Proceso {
	
	JobDetail obtenerJobDetail();
}
