package OtrosSensores;
/*
 * Clase que sirve para emular un sensor adicional al de posicion Geografica(GNSS)
 */
import com.google.common.eventbus.EventBus;

import Comun.Sensor;

public class SensorTermometro implements Sensor{

	private EventBus thisEB; 
	private Temperatura temperatura = new Temperatura("32 grados");

	public void setBus(EventBus bus) {
		thisEB=bus;
	}
	public void start() {
		thisEB.post(temperatura);
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
