package GNSS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ClasesDelSistema.Fecha;
/*
 *Clase de apoyo que contiene operaciones que se necesitan en las clases
 *NmeatoJson y SampleServidor 
 */
public class Operaciones 
{
	/*
	 * almacena un linea en el archivo coordenadas.txt al final del archivo cada vez que se llama
	 * @linea es el parametro de entrada del metodo, su contenido se almacena en una nueva linea de coordenadas.txt
	 */
	public static void guardar(String linea)
	{
		String filename="";  //String que contiene la ruta del archivo usado en el metodo guardar
		File newDir=new File("coordenadasHistory");
		newDir.mkdir();

		filename= "coord-" + Fecha.getFecha()+".txt";
		
		
		 try
		 {
		    File file = new File( newDir,filename ); //archivo que contiene un historico de las coordenadas(longitud-latitud) 
		    										   //de un bus

		    // si no existe el archivo
		    if ( ! file.exists( ) )
		    {
		        file.createNewFile( );
		    }
		    //fw y bw son elementos para escritura de archivos
		    FileWriter fw = new FileWriter( "coordenadasHistory/"+filename,true );
		    BufferedWriter bw = new BufferedWriter( fw );
		    bw.write( linea ); //guardo el string
		    bw.newLine(); // creo un salto de linea en el archivo
		    bw.close( );
		 }
		 catch( IOException e )
		 {
		 System.out.println("Error: " + e);
		 e.printStackTrace( );
		 }
		} 
	
}
