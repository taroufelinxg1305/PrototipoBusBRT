package ArmarMensaje;

import javax.json.Json;
import javax.json.JsonObject;

import com.google.common.eventbus.Subscribe;

import ClasesDelSistema.Coordenadas;
import ClasesDelSistema.DispBus;
import ClasesDelSistema.Fecha;
import OtrosSensores.Temperatura;

public class CrearMensajeJson {
	private Coordenadas coorToSend;
	private DispBus esteDisp;
	private Temperatura temp;

	public String armarJson() {
		String input = "";

		if (coorToSend != null) {

			JsonObject Entrada = Json.createObjectBuilder()

					.add("Placa", esteDisp.getPlaca()).add("Tde",Fecha.getFechaAndTime())
					.add("Coordenada", Json.createObjectBuilder().add("Latitud", "" + coorToSend.getLatitud())
							.add("Longitud", "" + coorToSend.getLongitud())
							.add("CodigoDispo", esteDisp.getCodDispo())
									.add("Temperatura", temp.getTemp()))
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
	@Subscribe
	public void envDisp(DispBus dbus) {
		esteDisp=dbus;
	}
	@Subscribe
	public void envTemp(Temperatura temp) {
		this.temp=temp;

	}
}
