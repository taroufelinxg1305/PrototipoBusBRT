package Lanzador;

import java.io.StringReader;
/*
 * Clase principal del sistema la cual se encarga de iniciar todos los sensores,
 * e iniciar los procesos de armado y enviado de mensajes a la plataforma cloudBRT
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import com.google.common.eventbus.EventBus;
import ArmarMensaje.CrearMensajeJson;
import ClasesDelSistema.Fecha;
import ClasesDelSistema.Propiedades;
import Comun.Sensor;
import EnviarMensaje.EnvioRestClient;
import EnviarMensaje.IniciarRecorrido;
import GNSS.MyGnssSensor;
import GNSS.NmeatoDatos;
import OtrosSensores.SensorTermometro;
import Persistencia.GuardarMensajes;

public class Launcher {

	private static Propiedades EsteVehiculoProperties = new Propiedades();
	private static MyGnssSensor gpsSensor = new MyGnssSensor(EsteVehiculoProperties.getPtoTCP());
	private static SensorTermometro myTempSensor = new SensorTermometro();
	private static CrearMensajeJson cmj = new CrearMensajeJson();
	private static EnvioRestClient erc = new EnvioRestClient(EsteVehiculoProperties.getUriServicio());
	private static GuardarMensajes gm = new GuardarMensajes();
	private static EventBus myEventBus = new EventBus();

	public static void main(String[] args) {

		myEventBus.register(cmj);
		gpsSensor.setBus(myEventBus);
		// System.out.println(EsteVehiculoProperties.getPlaca()+","+EsteVehiculoProperties.getCodDispo()+","+EsteVehiculoProperties.getUriServicio()+","+EsteVehiculoProperties.getPtoTCP());
		myEventBus.post(EsteVehiculoProperties);
		myTempSensor.setBus(myEventBus);
		myTempSensor.start();

		Thread t = new Thread(new Runnable() {
			public void run() {
				gpsSensor.start();
			}
		});
		t.start();
		erc.setArmarMensaje(cmj);
		gm.setArmarMensaje(cmj);
		IniciarRecorrido.iniciarPAM();
		IniciarRecorrido.iniciarReco();
		
			
			// Crea el scheduler
			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
			// Programa la ejecución cada 2 segundos del hilo (servicio). La
			// ejecuión empieza a los 3 segundos
			executor.scheduleAtFixedRate(erc, 2, 5, TimeUnit.SECONDS);
			// Crea el scheduler
			ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
			// Programa la ejecución cada 2 segundos del hilo (servicio). La
			// ejecuión empieza a los 5 segundos
			executor2.scheduleAtFixedRate(gm, 2, 5, TimeUnit.SECONDS);
			
		
	}

}
