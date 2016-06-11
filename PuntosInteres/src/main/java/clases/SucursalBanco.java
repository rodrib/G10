package clases;

public class SucursalBanco extends TipoPoi {

	@Override
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return super.distanciaDefecto;
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		// TODO Auto-generated method stub
		return false;
	}

}
