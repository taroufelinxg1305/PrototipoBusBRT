package EnviarMensaje;
/*
 * Cliente RestFul que consume un servicio sea local o remoto y le envia un contenido tipo json
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EnvioRestClient {
	
	/*
	 * Funcion de envio del contenido Json
	 * @param jsonToSend Este es un String el cual se crea por el metodo toString de un jsonObject
	 */
	public static void enviar(String jsonToSend)
	{
		try {

			URL url = new URL(
					//"http://localhost:8080/rutasBuses-1/apirutas/ubicacion/envioWilson");
			
			"http://localhost:8080/cloudBRT/api/colector/buses");

			String input =jsonToSend;  
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			
   
			OutputStream os = conn.getOutputStream();
			//DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			os.write(input.getBytes());
			os.flush();
			//wr.writeBytes(input);
			//wr.flush();
		//	wr.close();
				if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

		 BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("\nEnviado a la plataforma CloudBRT .... ");
			while ((output = br.readLine()) != null) {

				System.out.println(output+"\n");   
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}

}
