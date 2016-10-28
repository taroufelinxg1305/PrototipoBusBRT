package GNSS;

import java.io.IOException;
import java.net.UnknownHostException;
import com.google.common.eventbus.EventBus;

import Comun.Sensor;

public class MyGnssSensor implements Sensor {
	
	private MyTCPLocalServer tcpServer;
	

	@Override
	public void setBus(EventBus bus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		tcpServer = new MyTCPLocalServer();
		try{
			tcpServer.startTcpServer();
		}
		catch(IOException io)
		{
			System.out.println("problemas iniciando el Server");
			io.printStackTrace();
		}		
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
