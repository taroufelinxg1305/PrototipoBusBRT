package Lanzador;

import ArmarMensaje.CrearMensajeJson;
import ClasesDelSistema.Propiedades;
import EnviarMensaje.EnvioRestClient;
import GNSS.MyGnssSensor;
import OtrosSensores.SensorTermometro;
import Persistencia.GuardarMensajes;

import com.google.common.eventbus.EventBus;

/**
 * Clase principal del sistema la cual se encarga de iniciar todos los sensores,
 * e iniciar los procesos de armado y enviado de mensajes a la plataforma
 * cloudBRT
 */
public class Launcher {

	public static void main(String[] args) {

		Propiedades propiedades = new Propiedades();
		MyGnssSensor gpsSensor = new MyGnssSensor(propiedades.getPtoTCP());
		SensorTermometro myTempSensor = new SensorTermometro();
		CrearMensajeJson cmj = new CrearMensajeJson();
		EnvioRestClient erc = new EnvioRestClient(propiedades.getUriServicio());
		GuardarMensajes gm = new GuardarMensajes();
		EventBus myEventBus = new EventBus();
		myEventBus.register(cmj);		
		myEventBus.post(propiedades);
		
		myTempSensor.setBus(myEventBus);
		myTempSensor.start();
		
		gpsSensor.setBus(myEventBus);
		gpsSensor.start();
		
		erc.setProductorMensaje(cmj);
		gm.setProductorMensaje(cmj);

		erc.start();
		gm.start();

		

	}

}
