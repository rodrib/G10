package Utiles;

import clases.*;

public final class Util {

	public static float CalcularDistancia(Locacion locacionUno,
			Locacion locacionDos) {
		return CalcularDistancia(locacionUno.getCoordenada().getLatitud(),
				locacionUno.getCoordenada().getLongitud(), locacionDos
						.getCoordenada().getLatitud(), locacionDos
						.getCoordenada().getLongitud());
	}

	public static float CalcularDistancia(float lat1, float lng1, float lat2,
			float lng2) {
		double earthRadius = 6371000;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist;
	}
}
