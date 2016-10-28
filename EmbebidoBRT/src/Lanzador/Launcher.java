package Lanzador;

import GNSS.MyGnssSensor;

public class Launcher {
	
	private static MyGnssSensor gpsSensor = new MyGnssSensor();
	
	

	public static void main(String[] args) {
		
		gpsSensor.start();
		System.out.println("proceso finalizado");
	}

}
