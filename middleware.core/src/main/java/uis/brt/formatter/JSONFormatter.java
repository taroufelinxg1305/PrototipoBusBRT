package uis.brt.formatter;

/*
 * Clase en la que se ensambla el mensaje Json que se va a enviar a la plataforma CloudBRT,
 *  Tambien contiene los escuchadores del eventBus que le envian los datos del bus y de los sensores
 */
import javax.json.Json;
import javax.json.JsonObject;

import uis.brt.config.ConfigAdmin;
import uis.brt.sender.RestSender;
import uis.brt.sensor.api.Coordinate;
import uis.brt.sensor.api.Message;
import uis.brt.util.Fecha;

import com.google.common.eventbus.Subscribe;

public class JSONFormatter {
	private ConfigAdmin esteDisp; // Almacena los datos del vehiculo y
									// dispositivo
	private Coordinate coorToSend; // Contine la ultima coordenada recibida

	private int proximaParada = 1; // contiene un entero que representa la
									// siguiente
									// parada de la ruta, es de utilidad para la
									// plataforma cloud

	/*
	 * Metodo encargado de generar un String apartir de un JsonObject armado con
	 * la informacion del sistema
	 */

	public String armarJson() {

		String input = "";
		//proximaParada = EnvioRestClient.getProxpar();
		proximaParada = 10;
		String placa = esteDisp.getProperty("placa");
		String codigo = esteDisp.getProperty("codigo");
		if (coorToSend != null) {
			JsonObject Entrada = Json
					.createObjectBuilder()
					.add("Placa", placa)
					.add("Tde", Fecha.getFechaAndTime())
					.add("ProximaParada", proximaParada)
					.add("Coordenada",
							Json.createObjectBuilder()
									.add("Latitud",
											"" + coorToSend.getLatitud())
									.add("Longitud",
											"" + coorToSend.getLongitud())
									.add("CodigoDispo", codigo)
									.add("Temperatura", "50")) // Cambiar aqui
					.build();
			input = Entrada.toString();
		}
		return input;
		
	}
	

	/*
	 * Metodo que se activa cuando el eventBus usa el metodo post con una
	 * Coordenada de parametro
	 * 
	 * @param c Esta es la Coordenadas, enviada desde tcpServer
	 */
	@Subscribe
	public void envCoordenadas(Coordinate c) {
		System.out.println("coordenadas tcpserver(" + c.getLatitud() + ","
				+ c.getLongitud() + ")");
		coorToSend = c;
	}

	/*
	 * Metodo que se activa cuando el eventBus usa el metodo post con un DispBus
	 * de parametro
	 * 
	 * @param dbus Este es el DispBus, enviado desde el launcher
	 */
	@Subscribe
	public void envDisp(ConfigAdmin dbus) {		
		esteDisp = dbus;
	}

	@Subscribe
	public void receiveData(Message message) {
		System.out.println("----- Receiving a message from a sensor -----");
		System.out.println("Id: " + message.getId());
	}


}
