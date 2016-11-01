package ArmarMensaje;

import javax.json.Json;
import javax.json.JsonObject;

import com.google.common.eventbus.Subscribe;

import ClasesDelSistema.Coordenadas;
import ClasesDelSistema.Fecha;

public class CrearMensajeJson {
	private Coordenadas coorToSend;

	public String armarJson() {
		String input = "";

		if (coorToSend != null) {

			JsonObject Entrada = Json.createObjectBuilder()

					.add("Placa", "XDB725").add("Tde",Fecha.getFechaAndTime())
					.add("Coordenada", Json.createObjectBuilder().add("Latitud", "" + coorToSend.getLatitud())
							.add("Longitud", "" + coorToSend.getLongitud()))
					.build();
			input = Entrada.toString();
		}
		return input;
	}

	@Subscribe
	public void envCoordenadas(Coordenadas c) {
		//System.out.println("coordenadas tcpserver(" + c.getLatitud() + "," + c.getLongitud() + ")");
		coorToSend = c;

	}
}
