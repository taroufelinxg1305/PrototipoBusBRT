package uis.brt.sensor.gnss.serial;

import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import uis.brt.sensor.api.Coordinate;
import uis.brt.sensor.api.Sensor;

import com.fazecast.jSerialComm.SerialPort;
import com.google.common.eventbus.EventBus;

public class GNSSSensor implements Sensor, Runnable {

	private EventBus eventBus;
	private String serialPortName;
	private SerialPort serialPort;

	public GNSSSensor() {

	}

	public void setBus(EventBus bus) {
		eventBus = bus;
	}

	public void start() {
		

		// Abriendo el puerto Serial
		
		System.out.println("Starting GNSS Serial");
		
		serialPort = SerialPort.getCommPort(serialPortName);
		if (serialPort != null) {
			serialPort.setBaudRate(115200);
			serialPort.openPort();
			ScheduledExecutorService executor = Executors
					.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(this, 5, 3, TimeUnit.SECONDS);
		}
	}

	public void stop() {
		// TODO Auto-generated method stub

	}

	public void configure(Properties props) {
		serialPortName = props.getProperty("gnss.serial.port");

	}

	public void run() {
		System.out.println("--------------------------");
		if (serialPort.bytesAvailable() > 0) {

			byte[] readBuffer = new byte[serialPort.bytesAvailable()];

			int numRead = serialPort.readBytes(readBuffer, readBuffer.length);
			String str = new String(readBuffer, StandardCharsets.US_ASCII);
			System.out.println("Total " + numRead + " bytes.");

			Coordinate currentCoord = NmeatoDatos.separarTokenAndGetCoor(str);
			if (currentCoord != null)
				eventBus.post(currentCoord);

		}

	}

}
