package GNSS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.common.eventbus.EventBus;
import ClasesDelSistema.Coordenadas;

public class MyTCPLocalServer {
	private EventBus eb;
	private Coordenadas currentCoord;
	private int puertoTCP;

	public MyTCPLocalServer(int puerto) {
		puertoTCP= puerto;
	}
	public Coordenadas getCurrentCoord() {
		return currentCoord;
	}
	public void setBus(EventBus bus) {
		eb = bus;
	}

	public void startTcpServer() throws IOException {
		System.out.println("iniciando el server ...");

		// Define que el socket escuchara en el puerto que esta disponible para comunicacion TCP
		ServerSocket listener = new ServerSocket(puertoTCP);
		System.out.println("Servidor iniciado\n");

		try {
			while (true) {
				// Abre el socket y acepta las conexiones
				Socket socket = listener.accept(); // verifica si la conexion
													// fue exitosa
				try {

					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					while (true) {
						// Lee la linea de texto que llega del cliente
						String input = in.readLine();
						if (input == null || input.equals(".")) {
							break;
						}
						// Hace el tratamiento al texto recibido, en este caso
						// imprimir en la consola
						currentCoord = NmeatoDatos.separarTokenAndGetCoor(input);
						if(currentCoord!=null)eb.post(currentCoord);
					}
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}

}
