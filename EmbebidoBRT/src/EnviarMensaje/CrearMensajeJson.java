package EnviarMensaje;


import javax.json.Json;
import javax.json.JsonObject;

import ClasesDelSistema.Coordenadas;
import ClasesDelSistema.ThisBusCoordenadas;

public class CrearMensajeJson {

	public static String armarJson() {
		String input = "";

		JsonObject Entrada = Json.createObjectBuilder()

				.add("Placa", "XDB725").add("Tde", "2016/10/16 13:13:10").add("Coordenada", Json.createObjectBuilder()
						.add("Latitud", ""+ThisBusCoordenadas.getLatitud())
						.add("Longitud",""+ThisBusCoordenadas.getLongitud()))
						.build();
		input = Entrada.toString();
		
		return input;
	}
}
