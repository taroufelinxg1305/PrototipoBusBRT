package Lanzador;

/*
 * Clase principal del sistema la cual se encarga de iniciar todos los sensores,
 * e iniciar los procesos de armado y enviado de mensajes a la plataforma cloudBRT
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.google.common.eventbus.EventBus;
import ArmarMensaje.CrearMensajeJson;
import ClasesDelSistema.DispBus;
import Comun.Sensor;
import EnviarMensaje.EnvioRestClient;
import GNSS.MyGnssSensor;
import OtrosSensores.SensorTermometro;

public class Launcher implements Runnable {

	private static DispBus EsteVehiculo = new DispBus("XDB725", "0001");
	private static MyGnssSensor gpsSensor = new MyGnssSensor();
	private static SensorTermometro myTempSensor = new SensorTermometro();
	private static CrearMensajeJson cmj = new CrearMensajeJson();
	private static EventBus myEventBus = new EventBus();
	private static boolean serverIsOff = true;
	private static boolean clientIsOff = true;

	public static void main(String[] args) {

		myEventBus.register(cmj);
		gpsSensor.setBus(myEventBus);
		myEventBus.post(EsteVehiculo);
		myTempSensor.setBus(myEventBus);
		myTempSensor.start();

		// Crea el scheduler
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		// Programa la ejecución cada 2 segundos del hilo (servicio). La
		// ejecuión empieza a los 5 segundos
		executor.scheduleAtFixedRate(new Launcher(), 5, 5, TimeUnit.SECONDS);

	}

	public void run() {

		// myEventBus.post(""+gpsSensor.getCurrentCoords().getLatitud()+","+gpsSensor.getCurrentCoords().getLongitud());
		if (serverIsOff) {
			serverIsOff = false;
			Thread t = new Thread(new Runnable() {
				public void run() {
					gpsSensor.start();
				}
			});
			t.start();
		}

		if (clientIsOff) {
			clientIsOff = false;
			Thread t = new Thread(new Runnable() {
				public void run() {
					gpsSensor.startTcpClient();
				}
			});
			t.start();
		}

		String st = cmj.armarJson();
		if (!st.equals(""))
			EnvioRestClient.enviar(st);
	}

}
