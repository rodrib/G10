package clases;

import java.time.LocalDateTime;

public class CGP extends TipoPoi {

	@Override
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		// TODO Auto-generated method stub
		return miLocacion.getCodigoComuna() == otraLocacion.getCodigoComuna();
	}

	@Override
	public boolean estaDisponible(LocalDateTime fecha) {
		// TODO Auto-generated method stub
		return false;
	}
}
