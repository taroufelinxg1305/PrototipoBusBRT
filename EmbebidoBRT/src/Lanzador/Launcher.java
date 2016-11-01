package Lanzador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.google.common.eventbus.EventBus;
import ArmarMensaje.CrearMensajeJson;
import ClasesDelSistema.DispBus;
import EnviarMensaje.EnvioRestClient;
import GNSS.MyGnssSensor;

public class Launcher implements Runnable {

	private static DispBus EsteVehiculo = new DispBus("XDB725", "0001");
	private static MyGnssSensor gpsSensor = new MyGnssSensor();
	private static CrearMensajeJson cmj = new CrearMensajeJson();
	private static EventBus myEventBus = new EventBus();
	private static boolean serverIsOff = true;

	public static void main(String[] args) {
		
		myEventBus.register(cmj);
		gpsSensor.setBus(myEventBus);
		myEventBus.post(EsteVehiculo);

		// Crea el scheduler
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		// Programa la ejecución cada 2 segundos del hilo (servicio). La
		// ejecuión empieza a los 5 segundos
		executor.scheduleAtFixedRate(new Launcher(), 5, 5, TimeUnit.SECONDS);

	}

	@Override
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
		String st= cmj.armarJson();
		if(!st.equals(""))EnvioRestClient.enviar(st);
	}

}
