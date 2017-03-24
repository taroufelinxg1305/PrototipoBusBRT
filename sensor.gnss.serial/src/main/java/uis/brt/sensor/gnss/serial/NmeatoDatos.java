package uis.brt.sensor.gnss.serial;

import java.util.StringTokenizer;

import uis.brt.sensor.api.Coordinate;

public class NmeatoDatos {

	/*
	 * metodo que recibe un string(GGA nmea) y de el se estraen latitud y
	 * longitud absolutas
	 */
	public static Coordinate separarTokenAndGetCoor(String s) {

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
		Coordinate coord = null;
		if (isGgaLine(tok[0])) {
			double lat = 0, lon = 0; // en base a tok[2] y tok[3] lleno lat,
										// en base a tok[4] y tok[5] lleno lon

			lat = latitude2Decimal(tok[2], tok[3]);
			lon = longitude2Decimal(tok[4], tok[5]);
			
			/*
			if (tok[3].equals("N"))
				lat = degMinSecToDec(tok[2]);
			else if (tok[3].equals("S"))
				lat = (-1) * degMinSecToDec(tok[2]); // si la latitud pertenece
														// al hemisferio
			

			if (tok[5].equals("E"))
				lon = degMinSecToDec(tok[4]);
			else if (tok[5].equals("W"))
				lon = (-1) * degMinSecToDec(tok[4]);
			*/
			
			// si la longitud pertene al hemisferio
			// oeste,
			// la coordenada absoluta de longitud es
			// negativa

			coord = new Coordinate(lat, lon);

		}
		return coord;

	}

	public static boolean isGgaLine(String descriptor) {
		String st = descriptor.substring(descriptor.length() - 3);
		if (st.equals("GGA"))
			return true;
		return false;
	}

	static float latitude2Decimal(String lat, String NS) {
		float med = Float.parseFloat(lat.substring(2)) / 60.0f;
		med += Float.parseFloat(lat.substring(0, 2));
		if (NS.startsWith("S")) {
			med = -med;
		}
		return med;
	}

	static float longitude2Decimal(String lon, String WE) {
		float med = Float.parseFloat(lon.substring(3)) / 60.0f;
		med += Float.parseFloat(lon.substring(0, 3));
		if (WE.startsWith("W")) {
			med = -med;
		}
		return med;
	}

	public static double degMinSecToDec(String dms) {
		double deg, min, sec, dec = 0;
		int index = dms.indexOf('.');
		if (index >= 4) {
			try {
				deg = Double.parseDouble(dms.substring(0, index - 2));
				min = Double.parseDouble(dms.substring(index - 2, index));
				sec = Double.parseDouble(dms.substring(index + 1)) / 100;
				dec = deg + (min / 60) + (sec / 3600);
				return dec;
			} catch (NumberFormatException nfe) {
				System.out.println("fallo la conversion de coordenada");
				return dec;
			}
		}

		else {
			try {
				dec = Double.parseDouble(dms);
				return dec;
			} catch (NumberFormatException nfe) {
				System.out.println("fallo la conversion de coordenada");
				return dec;
			}

		}
	}

}
