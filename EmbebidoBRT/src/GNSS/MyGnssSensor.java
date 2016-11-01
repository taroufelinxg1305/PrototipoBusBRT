package GNSS;

import java.io.IOException;
import java.net.BindException;
import com.google.common.eventbus.EventBus;
import Comun.Sensor;

public class MyGnssSensor implements Sensor {

	private EventBus thisEB;
	private MyTCPLocalServer tcpServer;

	@Override
	public void setBus(EventBus bus) {
		thisEB = bus;
	}

	@Override
	public void start() {
		tcpServer = new MyTCPLocalServer();

		try {
			tcpServer.setBus(thisEB);
			tcpServer.startTcpServer();
		} catch (BindException be) {
			System.out.println("puerto 9091 ya esta en uso");
		} catch (IOException io) {
			System.out.println("problemas iniciando el Server");
			io.printStackTrace();
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
