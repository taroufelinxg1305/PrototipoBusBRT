package ClasesDelSistema;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/*   
 * Esta clase contiene la informacion del bus y del dispositivo embebido
 */
public class Propiedades extends Properties {
	
	public String getPlaca() {
		return getProperty("placa");
	}

	public String getCodDispo() {
		return getProperty("codigo");
	}

	public String getUriServicio() {
		return getProperty("uri");
	}

	public int getPtoTCP() {
		return Integer.parseInt(getProperty("puertoTcp"));
	}

	/*
	 * Constructor de la clase
	 */
	public Propiedades() {
		this("config.properties");
	}
	
	public Propiedades(String propFileName) {
		InputStream inputStream;

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		try {

			if (inputStream != null) {
				load(inputStream);
			} else {
				throw new FileNotFoundException("Archivo de configuraciones '" + propFileName + "' no se encontro en el Claspath");
			}
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	


}
