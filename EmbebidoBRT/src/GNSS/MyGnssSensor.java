package GNSS;

import java.io.IOException;
import java.net.BindException;
import java.net.UnknownHostException;

import com.google.common.eventbus.EventBus;
import Comun.Sensor;

public class MyGnssSensor implements Sensor {

	private EventBus thisEB;
	private MyTCPLocalServer tcpServer;
	private MyTCPLocalClientArchivo tcpClient;

	public void setBus(EventBus bus) {
		thisEB = bus;
	}

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
			tcpClient = new MyTCPLocalClientArchivo();
			tcpClient.startTcpClient();
		} catch (UnknownHostException unk) {
			unk.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void stop() {
		// TODO Auto-generated method stub

	}

}
