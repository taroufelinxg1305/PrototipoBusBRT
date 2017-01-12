package EnviarMensaje;

/*
 * Cliente RestFul que consume un servicio sea local o remoto y le envia un contenido tipo json
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;

import ArmarMensaje.CrearMensajeJson;

public class EnvioRestClient implements Runnable {
	CrearMensajeJson cmj;
	private String uri;
	private static boolean recTerminado = false;
	private static int proxpar;

	public static int getProxpar() {
		return proxpar;
	}

	public static boolean isRecTerminado() {
		return recTerminado;
	}

	public EnvioRestClient(String uriServicio) {
		uri = uriServicio;

	}

	public void setArmarMensaje(CrearMensajeJson mj) {
		cmj = mj;
	}
		/*
	 * Funcion de envio del contenido Json
	 * 
	 * @param jsonToSend Este es un String el cual se crea por el metodo
	 * toString de un jsonObject
	 */
	public void enviar(String jsonToSend) {
		try {

			URL url = new URL(uri);
			// "http://192.168.0.14:8080/cloudBRT/api/colector/buses");
			// "http://localhost:8080/cloudBRT/api/colector/buses");

			String input = jsonToSend;
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {

				System.out.println(output + "\n");

				JsonReader jsonReader = Json.createReader(new StringReader(output));
				JsonObject respuesta = jsonReader.readObject();
				jsonReader.close();
				proxpar  = respuesta.getInt("ProximaParada");				
				recTerminado= respuesta.getBoolean("Terminado");
			
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (ConnectException conn) {
			System.out.println("El servidor apache esta fuera de alcance o no esta encendido");
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public void run() {
		// TODO Auto-generated method stub
    
		if (cmj != null) {

			String st = cmj.armarJson();
			System.out.println(st);
			if (!st.equals(""))
				enviar(st);

		}

	}

}
