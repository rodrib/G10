package clases;

public class LocalComercial extends TipoPoi {
	RubroLocal rubro;

	@Override
	public float getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return rubro.getDistanciaMaxima();
	}

	@Override
	public boolean estaCerca(Locacion miLocacion, Locacion otraLocacion) {
		// TODO Auto-generated method stub
		return false;
	}

}
