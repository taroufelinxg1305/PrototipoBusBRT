/**
 * Esta clase se encarga de construir una fecha y hora de acuerdo al formato indicado a�o/mes/dia hora/minuto/segundo
 * y devuelve la misma como un String
 */

package ClasesDelSistema;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Fecha {

	private static String fecha;// cadena que contendra la fecha 
	/**
	 * Devuelve la fecha y la hora
	 * 
	 * @return fecha Este es un String que esta formado por "A�o/Mes/Dia Hora:Minuto:Segundo" del sistema
	 */
	public static String getFechaAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		fecha = dateFormat.format(date);
		return fecha;
	}
	
	/**
	 * Devuelve la fecha	 * 
	 * @return fecha  Este es un String que esta formado por "A�o-Mes-Dia" del sistema
	 */
	public static String getFecha() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		fecha = dateFormat.format(date);
		return fecha;
	}

}