package uis.brt.sensor.dummy;
/*
 * Clase que sirve para emular un sensor adicional al de posicion Geografica(GNSS)
 */
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import uis.brt.sensor.api.Message;
import uis.brt.sensor.api.Sensor;

import com.google.common.eventbus.EventBus;

public class SensorTermometro implements Sensor, Runnable {

	private EventBus thisEB; 


	public void setBus(EventBus bus) {
		thisEB=bus;
	}
	
	public void start() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(this, 1, 10, TimeUnit.SECONDS);
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void run() {
		int value = ThreadLocalRandom.current().nextInt(18, 33);
		Message message = new Message("Termometro", "" + value);
		thisEB.post(message);		
	}

	public void configure(Properties props) {
		Set keys = props.keySet(); // get set-view of keys
		Iterator itr = keys.iterator();

		while (itr.hasNext()) {
			String str = (String) itr.next();
			System.out.println("The value of " + str + " is "
					+ props.getProperty(str) + ".");
		}
		
	}
	

}
