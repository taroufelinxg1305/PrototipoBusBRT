package ServidorTCP;

import javax.json.Json;

/**
 * Una Clase sencilla que se encarga de manejar coordenadas
 * 
 *
 */
public class ThisBusCoordenadas {

	private static double currentLati =3.44;
	private static double currentLong =3.235;
	
	/**
	 * Retorna la latitud
	 * @return double
	 */
	public static double getLatitud() {
		return currentLati;
	}

	/**
	 * Modifica la latitud
	 * @param latitud
	 */
	public static void setLatitud(double latitud) {
		currentLati = latitud;
	}

	/**
	 * Retorna la Longitud
	 * @return double
	 */
	public static double getLongitud() {
		return currentLong;
	}

	/**
	 * Modifica la Longitud
	 * @param longitud
	 */
	public static void setLongitud(double longitud) {
		currentLong = longitud;
	}
	
	
	
	
}
