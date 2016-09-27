package ServidorTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPLocalServer {

	private static double currentLati=0; //son los valores promedio que se enviaran en algun momento a la plataforma cloudBRT
	private static double currentLong=0;
	public static double getCurrentLati() {
		return currentLati;
	}
	public static double getCurrentLong() {
		return currentLong;
	}
	static int moduloPromedio=0;
    public static void main(String[] args) throws IOException {
    	// Define que el socket escuchara en el puerto 9091
        ServerSocket listener = new ServerSocket(9091);
        double[] promedios= new double[2];
        try {
            while (true) {
            	// Abre el socket y acepta las conexiones
                Socket socket = listener.accept(); //verifica si la conexion fue exitosa
                System.out.print("Server has connected!\n");

                try {
                	
                	BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                	
                    while (true) {
                    	// Lee la linea de texto que llega del cliente
                        String input = in.readLine();                        
                        if (input == null || input.equals(".")) {
                            break;
                        }
                        // Hace el tratamiento al texto recibido, en este caso imprimir en la consola
                        
                        //System.out.println(input);
                     promedios=NmeatoDatos.SepararToken(input);
                     
                     
                     }
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
        
    }


}
