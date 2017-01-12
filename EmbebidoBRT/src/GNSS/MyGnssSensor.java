package GNSS;

import Comun.Sensor;

import com.google.common.eventbus.EventBus;

public class MyGnssSensor implements Sensor {

	private EventBus eventBus;
	private MyTCPLocalServer tcpServer;
	private int puerto;

	public MyGnssSensor(int port) {
		puerto= port;
	}

	public void setBus(EventBus bus) {
		eventBus = bus;
	}

	public void start() {
		tcpServer = new MyTCPLocalServer(puerto);
		tcpServer.setBus(eventBus);
		
		Thread thrTCPServer = new Thread(tcpServer);
		thrTCPServer.start();
	}

	public void stop() {
		// TODO Auto-generated method stub

	}

}
