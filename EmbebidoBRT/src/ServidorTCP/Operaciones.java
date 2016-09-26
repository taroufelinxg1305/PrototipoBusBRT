package ServidorTCP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*
 *Clase de apoyo que contiene operaciones que se necesitan en las clases
 *NmeatoJson y SampleServidor 
 */
public class Operaciones 
{
	
	String filename="";  //String que contiene la ruta del archivo usado en el metodo guardar
	
	/*
	 * almacena un linea en el archivo coordenadas.txt al final del archivo cada vez que se llama
	 * @linea es el parametro de entrada del metodo, su contenido se almacena en una nueva linea de coordenadas.txt
	 */
	public static void guardar(String linea)
	{
		 try
		 {
		    File file = new File( "coordenadas.txt" ); //archivo que contiene un historico de las coordenadas(longitud-latitud) 
		    										   //de un bus

		    // si no existe el archivo
		    if ( ! file.exists( ) )
		    {
		        file.createNewFile( );
		    }
		    //fw y bw son elementos para escritura de archivos
		    FileWriter fw = new FileWriter( file.getAbsoluteFile( ),true );
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
	/*
	 * Metodo usado para promediar 10 coordenadas
	 * @temp es un parametro de entrada, matriz que contiene un determinado numero de filas y dos columnas
	 * @return un vector de dos entradas (latitud, longitud)
	 */
	public static double[] promedioCoor(double[][] temp)
	{
		double[] promVec = new double[2]; //creo el vector de salida
		double pLat,pLon,sLat=0,sLon=0; //variables de promedios y sumas
		int nfilas= temp.length;  //averiguo el numero de filas de temp
		for(int i=0;i<nfilas;i++)
		{
			//recorro la matriz y obtengo la suma de las latitudes y la suma de las longitudes
			sLat+=temp[i][0];
			sLon+=temp[i][1];
		}
		pLat=sLat/nfilas; //prom latitud
		pLon=sLon/nfilas; //prom longitud
		promVec[0]=pLat;
		promVec[1]=pLon;
		return promVec;
	}
	
	
}
