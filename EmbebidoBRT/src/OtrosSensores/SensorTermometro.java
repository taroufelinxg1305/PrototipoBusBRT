package OtrosSensores;
/*
 * Clase que sirve para emular un sensor adicional al de posicion Geografica(GNSS)
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import Comun.Sensor;

import com.google.common.eventbus.EventBus;

public class SensorTermometro implements Sensor, Runnable {

	private EventBus thisEB; 


	public void setBus(EventBus bus) {
		thisEB=bus;
	}
	public void start() {
		// Crea el scheduler
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		// Programa la ejecución cada 2 segundos del hilo (servicio).
		// La ejecuión empieza a los 3 segundos
		executor.scheduleAtFixedRate(this, 1, 10, TimeUnit.SECONDS);
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void run() {
		int value = ThreadLocalRandom.current().nextInt(18, 33);
		Temperatura temperatura = new Temperatura(value + "");
		thisEB.post(temperatura);
		
	}
	

}
