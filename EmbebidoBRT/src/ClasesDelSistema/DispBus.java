package ClasesDelSistema;
/*   
 * Esta clase contiene la informacion del bus y del dispositivo embebido
 */
public class DispBus {
	private String placa;  //Placa del bus que lleva este dispositivo embebido
	private String codDispo;  //Codigo que identifica este dispositivo 
		
	
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
	/*
	 * Constructor de la clase
	 */
	public DispBus(String placa, String codDispo)
	{
		setPlaca(placa);
		setCodDispo(codDispo);
	}
	
}
