package uis.brt.launcher;

import uis.brt.config.ConfigAdmin;
import uis.brt.formatter.JSONFormatter;
import uis.brt.persistence.Persistence;
import uis.brt.sender.RestSender;
import uis.brt.sensor.admin.SensorAdmin;

import com.google.common.eventbus.EventBus;

/**
 * Clase principal del sistema la cual se encarga de iniciar todos los sensores,
 * e iniciar los procesos de armado y enviado de mensajes a la plataforma
 * cloudBRT
 */
public class Launcher {

	public static void main(String[] args) {

		ConfigAdmin propiedades = new ConfigAdmin();
		JSONFormatter cmj = new JSONFormatter();
		RestSender erc = new RestSender(propiedades.getProperty("uri"));
		Persistence gm = new Persistence();
		EventBus myEventBus = new EventBus();
		
		myEventBus.register(cmj);		
		myEventBus.post(propiedades);
		
		SensorAdmin sensorAdmin = new SensorAdmin(myEventBus, propiedades);
		sensorAdmin.startSensors();
			
		erc.setProductorMensaje(cmj);
		gm.setProductorMensaje(cmj);

		erc.start();
		gm.start();

	}

}
