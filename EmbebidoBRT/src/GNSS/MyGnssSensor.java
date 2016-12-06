package GNSS;

import java.io.IOException;
import java.net.BindException;
import java.net.UnknownHostException;

import com.google.common.eventbus.EventBus;
import Comun.Sensor;

public class MyGnssSensor implements Sensor {

	private EventBus thisEB;
	private MyTCPLocalServer tcpServer;
	private int puerto;

	public MyGnssSensor(int port) {
		puerto= port;
	}

	public void setBus(EventBus bus) {
		thisEB = bus;
	}

	public void start() {
		tcpServer = new MyTCPLocalServer(puerto);

		try {
			tcpServer.setBus(thisEB);
			tcpServer.startTcpServer();
		} catch (BindException be) {
			System.out.println("El puerto "+puerto+" ya esta en uso");
		} catch (IOException io) {
			System.out.println("problemas iniciando el Server");
			io.printStackTrace();
		}
	}

	public void stop() {
		// TODO Auto-generated method stub

	}

}
