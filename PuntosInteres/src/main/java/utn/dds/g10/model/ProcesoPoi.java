package utn.dds.g10.model;

import org.quartz.Job;

// Todos los procesos deben heredar de esta clase e implementar el m�todo execute() que exige la interfaz Job
public abstract class ProcesoPoi implements Job {

	private static Class SIGUIENTE_PROCESO;

	public static Class getSiguienteProceso() {
		return SIGUIENTE_PROCESO;
	}
	
	public static void setSiguienteProceso(Class siguienteProceso) {
		SIGUIENTE_PROCESO = siguienteProceso;
	}

//	Con el prop�sito de simplificar el c�digo se usa la API Reflection de Java
	public ProcesoListener getProcesoListener() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return (ProcesoListener) getClass().getClassLoader().loadClass(getClass().getName()+"Listener").newInstance();
	}

}
