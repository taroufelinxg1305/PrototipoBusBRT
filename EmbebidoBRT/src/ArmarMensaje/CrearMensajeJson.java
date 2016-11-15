package ArmarMensaje;

/*
 * Clase en la que se ensambla el mensaje Json que se va a enviar a la plataforma CloudBRT,
 *  Tambien contiene los escuchadores del eventBus que le envian los datos del bus y de los sensores
 */
import javax.json.Json;
import javax.json.JsonObject;
import com.google.common.eventbus.Subscribe;
import ClasesDelSistema.Coordenadas;
import ClasesDelSistema.Propiedades;
import ClasesDelSistema.Fecha;
import OtrosSensores.Temperatura;

public class CrearMensajeJson {
	private Propiedades esteDisp;   //Almacena los datos del vehiculo y dispositivo
	private Coordenadas coorToSend;	//Contine la ultima coordenada recibida
	private Temperatura temp;	//contine una constante que simula el funcionamiento de un senson adicional
	
	/*
	 *Metodo encargado de generar un String apartir de un JsonObject armado con la informacion del sistema 
	 */
	public String armarJson() {
		String input = "";
		if (coorToSend != null) {

			JsonObject Entrada = Json.createObjectBuilder().add("Placa", esteDisp.getPlaca())
					.add("Tde", Fecha.getFechaAndTime())
					.add("Coordenada",
							Json.createObjectBuilder().add("Latitud", "" + coorToSend.getLatitud())
									.add("Longitud", "" + coorToSend.getLongitud())
									.add("CodigoDispo", esteDisp.getCodDispo()).add("Temperatura", temp.getTemp()))
					.build();
			input = Entrada.toString();
		}
		return input;
	}
	
	/*
	 * Metodo que se activa cuando el eventBus usa el metodo post con una Coordenada de parametro
	 * @param c Esta es la Coordenadas, enviada desde tcpServer
	 */	
	@Subscribe
	public void envCoordenadas(Coordenadas c) {
		System.out.println("coordenadas tcpserver(" + c.getLatitud() + "," + c.getLongitud() + ")");
		coorToSend = c;
	}

	/*
	 * Metodo que se activa cuando el eventBus usa el metodo post con un DispBus de parametro
	 * @param dbus Este es el DispBus, enviado desde el launcher
	 */
	@Subscribe
	public void envDisp(Propiedades dbus) {
		esteDisp = dbus;
	}

	/*
	 * Metodo que se activa cuando el eventBus usa el metodo post con una Temperatura de parametro
	 * @param temp Esta es la Temperatura, enviada desde tcpServer
	 */
	@Subscribe
	public void envTemp(Temperatura temp) {
		this.temp = temp;

	}
}
