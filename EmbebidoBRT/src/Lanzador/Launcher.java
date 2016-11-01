package Lanzador;

import com.google.common.eventbus.EventBus;

import ArmarMensaje.CrearMensajeJson;
import GNSS.MyGnssSensor;

public class Launcher {
	
	private static MyGnssSensor gpsSensor = new MyGnssSensor();
	private static EventBus myEventBus = new EventBus();
	

	public static void main(String[] args) {
		while(true)
		{
		gpsSensor.start();
		System.out.println("iniciado el broker");
		myEventBus.register(new CrearMensajeJson());
		myEventBus.post("Enviado desde lanzador");
		}

	}

}
