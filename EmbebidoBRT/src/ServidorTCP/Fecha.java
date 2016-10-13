/**
 * Esta clase se encarga de construir una fecha y hora de acuerdo al formato indicado a�o/mes/dia hora/minuto/segundo
 * y devuelve la misma como un String
 */

package ServidorTCP;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Fecha {

	private String fecha;//cadena que contendra la fecha 
	private static Fecha fechaClass;
	
	/**
	 *Constructor sin parametros donde se define el formato y se construye la fecha 
	 */
	private Fecha()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
		fecha=dateFormat.format(date);
	}
	
	/**
	 * Devuelve la fecha
	 * @return fecha
	 */
	public String getFecha()
	{
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	     Timestamp stamp = new Timestamp(System.currentTimeMillis());
	     Date date = new Date(stamp.getTime());
	     fecha=dateFormat.format(date);
		return fecha;
	}
	
	public static Fecha getFechaClass(){
		if(fechaClass == null){
			fechaClass = new Fecha();
		}
		return fechaClass;
	}
}