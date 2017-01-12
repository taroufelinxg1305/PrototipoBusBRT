package EnviarMensaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IniciarRecorrido {
	
	public static void iniciarPAM() {
		try {
			URL obj = new URL(
					"http://localhost:8080/cloudBRT/api/colector/parqueAutomotor/iniciar");
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
			System.out.println(response.toString());

		} catch (IOException e) {
			System.out.println("error iniciando parque automotor");
		}

	}

	public static void iniciarReco() {
		try {
			URL obj = new URL(
					"http://localhost:8080/cloudBRT/api/colector/itinerario/iniciar/I3T3");
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
			System.out.println(response.toString());

		} catch (IOException e) {
			System.out.println("error iniciando recorrido");
		}

	}
}
