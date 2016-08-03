package utn.dds.g10.entidades;

import java.util.ArrayList;

public class CentroDTO {
		
	ArrayList<CGP> listaCGP ;
	
	public CentroDTO(){		
		listaCGP = new ArrayList<CGP>();
	}

	public ArrayList<CGP> getListaCGP() {
		return listaCGP;
	}

	public void setListaCGP(ArrayList<CGP> listaCGP) {
		this.listaCGP = listaCGP;
	}
	
}
