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

	public Coordenadas getCurrentCoord() {
		return currentCoord;
	}
	public void setBus(EventBus bus) {
		eb = bus;
	}

	public void startTcpServer() throws IOException {
		System.out.println("iniciando el server ...");

		// Define que el socket escuchara en el puerto 9091
		ServerSocket listener = new ServerSocket(9091);
		System.out.println("Servidor iniciado");

		try {
			while (true) {
				// Abre el socket y acepta las conexiones
				Socket socket = listener.accept(); // verifica si la conexion
													// fue exitosa
				System.out.print("Server has connected!\n");

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
						eb.post(currentCoord);
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
