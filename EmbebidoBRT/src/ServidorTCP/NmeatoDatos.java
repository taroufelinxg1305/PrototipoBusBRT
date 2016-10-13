package ServidorTCP;

import java.util.StringTokenizer;

public class NmeatoDatos {

	/*
	 * metodo que recibe un string(GGA nmea) y de el se estraen latitud y
	 * longitud absolutas
	 */

	public static double[] SepararToken(String s) {

		// entrada=
		// "$GPGGA,181908.00,3404.7041778,N,07044.3966270,W,4,13,1.00,495.144,M,29.200,M,0.10,0000*40";
		String entrada = s;
		StringTokenizer st = new StringTokenizer(entrada, ",");
		String[] tok = new String[st.countTokens()]; // vector par almacenar
														// cada datos separado
														// por coma de la
														// entrada
		int cont = 0;

		while (st.hasMoreTokens()) {

			tok[cont] = st.nextToken();
			cont++;
		}
		String lat = "", lon = ""; // en base a tok[2] y tok[3] lleno lat, en
									// base a tok[4] y tok[5] lleno lon
		if (tok[3].equals("N"))
			lat = tok[2];
		else if (tok[3].equals("S"))
			lat = "-" + tok[2]; // si la latitud pertenece al hemisferio sur, la
								// coordenada absoluta de latitud es negatica
		if (tok[5].equals("E"))
			lon = tok[4];
		else if (tok[5].equals("W"))
			lon = "-" + tok[4]; // si la longitud pertene al hemisferio oeste,
								// la coordenada absoluta de longitud es
								// negativa

		System.out.println("latitud:  " + lat);

		System.out.println("longitud: " + lon);
		String linea = "" + TCPLocalServer.getEsteBus().getFechaBus() + "," + TCPLocalServer.getEsteBus().getCodDispo()
				+ "," + lat + "," + lon;
		Operaciones.guardar(linea); // almacena un historico de los pares de
									// coordenadas absolutas(latitud,longitud)
		double[] currentCoor = new double[2];
		currentCoor[0] = 0;
		currentCoor[1] = 0;

		try {
			currentCoor[0] = Double.parseDouble(lat);
			currentCoor[1] = Double.parseDouble(lon);

		} catch (NumberFormatException nfe) {
			System.out.println("fallo la conversion de coordenada");
		}

		return currentCoor;
	}

}
