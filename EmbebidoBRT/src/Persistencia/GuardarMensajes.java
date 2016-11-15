package Persistencia;

import ArmarMensaje.CrearMensajeJson;

public class GuardarMensajes implements Runnable {
	CrearMensajeJson cmj;
	
	public void setArmarMensaje(CrearMensajeJson mj){
		cmj= mj;
	}

	public void run() {
		// TODO Auto-generated method stub
		String st=cmj.armarJson();
		if (!st.equals(""))
		Operaciones.guardar(st);
	}

}
