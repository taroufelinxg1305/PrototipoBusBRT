package GNSS;

import java.io.IOException;
import java.net.BindException;
import java.net.UnknownHostException;

import com.google.common.eventbus.EventBus;
import Comun.Sensor;

public class MyGnssSensor implements Sensor {

	private EventBus thisEB;
	private MyTCPLocalServer tcpServer;
	private MyTCPLocalClient tcpClient;

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

	public void startTcpClient() {
		try {
			tcpClient = new MyTCPLocalClient();
			tcpClient.startTcpClient();
		} catch (UnknownHostException unk) {
			unk.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
