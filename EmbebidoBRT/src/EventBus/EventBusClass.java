package EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.EventBus;

import ClasesDelSistema.Coordenadas;
import ClasesDelSistema.ThisBusCoordenadas;

public class EventBusClass implements Runnable{
	public static Coordenadas c = new Coordenadas(0, 0); 
	
	public static void main(String[] args) {
		// Crea el scheduler
				ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

				// Programa la ejecución cada 2 segundos del hilo (servicio). La
				// ejecuión empieza a los 5 segundos
				executor.scheduleAtFixedRate(new EventBusClass(), 5, 2, TimeUnit.SECONDS);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		EventBus eventBus = new EventBus();
		eventBus.register(new ParaEnvioListener());
		if (c != null) {
			eventBus.post(c);
			System.out.println("");
		} else
		{
			System.out.println("No hay coordenadas que reportar");
		}
		

	}
}