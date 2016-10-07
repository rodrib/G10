package utn.dds.g10.jobs;

import org.quartz.JobListener;

import utn.dds.g10.model.ProcesoListener;



public class ProcesoAgregarAccionesListener extends ProcesoListener implements JobListener {

	@Override
	protected void rollback() {
		System.out.println("Rollback de AgregarAcciones");
	}

}