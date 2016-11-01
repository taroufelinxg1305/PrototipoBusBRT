package ArmarMensaje;


import javax.json.Json;
import javax.json.JsonObject;

import com.google.common.eventbus.Subscribe;

import ClasesDelSistema.Coordenadas;

public class CrearMensajeJson {

	public static String armarJson() {
		String input = "";

		JsonObject Entrada = Json.createObjectBuilder()

				.add("Placa", "XDB725").add("Tde", "2016/10/16 13:13:10").add("Coordenada", Json.createObjectBuilder()
						.add("Latitud", "")
						.add("Longitud",""))
						.build();
		input = Entrada.toString();
		
		return input;
	}
	
	  @Subscribe
	    public void envCoordenadas(String st) {
	        System.out.println("do task("+st +")");
	    }
}
