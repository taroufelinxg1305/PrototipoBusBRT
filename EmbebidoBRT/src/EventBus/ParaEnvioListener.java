package EventBus;
 
import com.google.common.eventbus.Subscribe;

import ClasesDelSistema.Coordenadas;
import ClasesDelSistema.DispBus;
 
public class ParaEnvioListener {
	private Coordenadas PECoord;
	private DispBus PEdispBus;
	
	
    @Subscribe
    public void envCoordenadas(Coordenadas c) {
        System.out.println("do task(" + c.getLatitud()+" ,"+c.getLongitud() + ")");
    }
}