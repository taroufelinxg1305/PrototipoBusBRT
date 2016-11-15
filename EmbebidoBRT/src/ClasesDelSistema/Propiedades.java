package ClasesDelSistema;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/*   
 * Esta clase contiene la informacion del bus y del dispositivo embebido
 */
public class Propiedades {
	private String placa; // Placa del bus que lleva este dispositivo embebido
	private String codDispo; // Codigo que identifica este dispositivo
	private String uriServicio; // uri donde esta el servicio de recolectar en
								// CloudBRT
	private int ptoTCP; // el puerto que va a utilizar el mytcpserver;

	public String getPlaca() {
		return placa;
	}

	public String getCodDispo() {
		return codDispo;
	}

	public String getUriServicio() {
		return uriServicio;
	}

	public int getPtoTCP() {
		return ptoTCP;
	}

	/*
	 * Constructor de la clase
	 */
	public Propiedades() {
		setPropiedades(getPropiedades());
	}

	private void setPropiedades(Properties prop) {
		placa = prop.getProperty("placa");
		codDispo = prop.getProperty("codigo");
		uriServicio = prop.getProperty("uri");
		ptoTCP = Integer.parseInt(prop.getProperty("puertoTcp"));
	}

	private Properties getPropiedades() {

		// se crea una instancia a la clase Properties
		Properties propiedades = new Properties();
		// se leen el archivo "configuraciones"
		String propFileName = "config.properties";
		InputStream inputStream;

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		try {

			if (inputStream != null) {
				propiedades.load(inputStream);
			} else {
				throw new FileNotFoundException("Archivo de configuraciones '" + propFileName + "' no se encontro en el Claspath");
			}
			inputStream.close();

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return propiedades;
	}

}
