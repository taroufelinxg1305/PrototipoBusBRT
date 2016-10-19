package EventBus;

import com.google.common.eventbus.Subscribe;

public class OtroSimpleListener {
	
	@Subscribe public void tarea1(String s)
	{
		System.out.println("imprime este contenido y le añade : " + s);
	}

}
