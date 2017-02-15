package Persistencia;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ArmarMensaje.CrearMensajeJson;

public class GuardarMensajes implements Runnable {
	CrearMensajeJson cmj;

	public void setProductorMensaje(CrearMensajeJson mj) {
		cmj = mj;
	}

	public void run() {
		// TODO Auto-generated method stub
		String st = cmj.armarJson();
		if (!st.equals("")) {
			Operaciones.guardar(st);
			System.out.println("Guardando mensaje en persistencia");
		}
	}

	/**
	 * Este método se invoca para iniciar el servicio de persistencia de los
	 * datos
	 */
	public void start() {
		// Crea el scheduler
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		// Programa la ejecución cada 2 segundos del hilo (servicio).
		// La ejecuión empieza a los 3 segundos
		executor.scheduleAtFixedRate(this, 3, 10, TimeUnit.SECONDS);
	}

}
