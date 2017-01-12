package Lanzador;

import ArmarMensaje.CrearMensajeJson;
import ClasesDelSistema.Propiedades;
import EnviarMensaje.EnvioRestClient;
import EnviarMensaje.IniciarRecorrido;
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

	private static Propiedades EsteVehiculoProperties = new Propiedades();
	private static MyGnssSensor gpsSensor = new MyGnssSensor(
			EsteVehiculoProperties.getPtoTCP());
	private static SensorTermometro myTempSensor = new SensorTermometro();
	private static CrearMensajeJson cmj = new CrearMensajeJson();
	private static EnvioRestClient erc = new EnvioRestClient(
			EsteVehiculoProperties.getUriServicio());
	private static GuardarMensajes gm = new GuardarMensajes();
	private static EventBus myEventBus = new EventBus();

	public static void main(String[] args) {

		myEventBus.register(cmj);
		gpsSensor.setBus(myEventBus);
		myEventBus.post(EsteVehiculoProperties);
		myTempSensor.setBus(myEventBus);
		myTempSensor.start();

		gpsSensor.start();

		erc.setProductorMensaje(cmj);
		gm.setProductorMensaje(cmj);

		erc.start();
		gm.start();

		IniciarRecorrido.iniciarPAM();
		IniciarRecorrido.iniciarReco();

	}

}
