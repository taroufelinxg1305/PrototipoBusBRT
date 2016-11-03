package Comun;
/**
 * Clase que se puede adaptar para todos los futuros sensores que se integraran al sistema embebido;
 */
import com.google.common.eventbus.EventBus;

public interface Sensor {

	public void setBus(EventBus bus);
	
	public void start();
	
	public void stop();
	
}
