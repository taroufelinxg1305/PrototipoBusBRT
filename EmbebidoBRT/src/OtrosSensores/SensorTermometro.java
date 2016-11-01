package OtrosSensores;

import com.google.common.eventbus.EventBus;

import Comun.Sensor;

public class SensorTermometro implements Sensor{

	private EventBus thisEB;
	private Temperatura temperatura = new Temperatura("32 grados");

	@Override
	public void setBus(EventBus bus) {
		thisEB=bus;
	}
	@Override
	public void start() {
		thisEB.post(temperatura);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
