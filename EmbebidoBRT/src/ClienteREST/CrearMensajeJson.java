package ClienteREST;


import javax.json.Json;
import javax.json.JsonObject;

import ServidorTCP.Coordenadas;

public class CrearMensajeJson {

	public static String armarJson() {
		String input = "";

		JsonObject Entrada = Json.createObjectBuilder()

				.add("placa", "XDB725").add("coordenada", Json.createObjectBuilder()
						.add("latitud", Coordenadas.getLatitud())
						.add("longitud", Coordenadas.getLongitud()))
						.build();
		input = Entrada.toString();
		
		return input;
	}
}
