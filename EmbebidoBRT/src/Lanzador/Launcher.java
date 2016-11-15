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
import Persistencia.GuardarMensajes;

public class Launcher {

	private static DispBus EsteVehiculo = new DispBus("XDB725", "0001");
	private static MyGnssSensor gpsSensor = new MyGnssSensor();
	private static SensorTermometro myTempSensor = new SensorTermometro();
	private static CrearMensajeJson cmj = new CrearMensajeJson();
	private static EnvioRestClient erc = new EnvioRestClient();
	private static GuardarMensajes gm = new GuardarMensajes();
	private static EventBus myEventBus = new EventBus();

	public static void main(String[] args) {

		myEventBus.register(cmj);
		gpsSensor.setBus(myEventBus);
		myEventBus.post(EsteVehiculo);
		myTempSensor.setBus(myEventBus);
		myTempSensor.start();

		Thread t = new Thread(new Runnable() {
			public void run() {
				gpsSensor.start();
			}
		});
		t.start();

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				gpsSensor.startTcpClient();
			}
		});
		t2.start();

		erc.setArmarMensaje(cmj);
		gm.setArmarMensaje(cmj);

		// Crea el scheduler
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		// Programa la ejecución cada 2 segundos del hilo (servicio). La
		// ejecuión empieza a los 3 segundos
		executor.scheduleAtFixedRate(erc, 3, 5, TimeUnit.SECONDS);
		// Crea el scheduler
		ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
		// Programa la ejecución cada 2 segundos del hilo (servicio). La
		// ejecuión empieza a los 5 segundos
		executor2.scheduleAtFixedRate(gm, 5, 5, TimeUnit.SECONDS);

	}

}
