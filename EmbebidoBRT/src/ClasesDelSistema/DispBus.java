package ClasesDelSistema;
/*   
 * Esta clase contiene la informacion del bus y del dispositivo embebido
 */
public class DispBus {
	private String placa;
	private String codDispo;
		
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public void setCodDispo(String codDispo) {
		this.codDispo = codDispo;
	}
	public  String getPlaca() {
		return placa;
	}
	public  String getCodDispo() {
		return codDispo;
	}
	
	public DispBus(String placa, String codDispo)
	{
		setPlaca(placa);
		setCodDispo(codDispo);
	}
	
}
