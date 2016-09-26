package ClienteREST;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnviarPos implements Runnable{

	public static void main(String[] args)
	{
		  	  

		// Crea el scheduler
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		
		// Programa la ejecución cada 2 segundos del hilo (servicio). La ejecuión empieza a los 5 segundos
		executor.scheduleAtFixedRate(new EnviarPos(), 5, 2, TimeUnit.SECONDS);

	}
	@Override
	public void run() 
	{
		try {

			URL url = new URL(
					"http://localhost:8080/rutasBuses-1/apirutas/ubicacion/envioWilson");
			String input =""; 			
			
			
			
		     JsonObject Entrada = Json.createObjectBuilder()
		    		 
		    		         .add("placa", "XDB725")	
		    		         .add("coordenada",Json.createObjectBuilder()
		    		         
				    		 .add("latitud", 7.3431231443)
						     .add("longitud", 67.4567864324))
		    	     
		    	     .build();
				input= Entrada.toString();
				//System.out.println(input );
			
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
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);   
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
		
		
		
	}
}
