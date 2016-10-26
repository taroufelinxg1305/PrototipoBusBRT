package ServidorTCP;
/**
 * Una Clase sencilla que se encarga de manejar coordenadas
 * 
 *
 */
public class Coordenadas {

	private double latitud ;
	private double longitud ;

	/**
	 * Retorna la latitud
	 * 
	 * @return double
	 */
	public Coordenadas(double latitud, double longitud)
	{
		this.latitud=latitud;
		this.longitud=longitud;
	}
	
	public double getLatitud() {
		return latitud;
	}

	/**
	 * Modifica la latitud
	 * 
	 * @param latitud
	 */
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	/**
	 * Retorna la Longitud
	 * 
	 * @return double
	 */
	public double getLongitud() {
		return longitud;
	}

	/**
	 * Modifica la Longitud
	 * 
	 * @param longitud
	 */
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

}
