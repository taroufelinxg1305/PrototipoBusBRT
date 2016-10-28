package Comun;

import com.google.common.eventbus.EventBus;

public interface Sensor {

	public void setBus(EventBus bus);
	
	public void start();
	
	public void stop();
	
}
