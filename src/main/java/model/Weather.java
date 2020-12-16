package model;

/**
 * Description of Weather.
 * 
 * @author Massimo
 */
public class Weather {
	/**
	 * Description of the property Pressione.
	 */
	private double Pressione = 0;

	/**
	 * Description of the property TempPercepita.
	 */
	private double TempPercepita = 0;

	/**
	 * Description of the property giorno.
	 */
	private int giorno = 0;

	/**
	 * Description of the property Nome.
	 */
	private String Nome = "";

	/**
	 * Description of the property TempMax.
	 */
	private double TempMax = 0;

	/**
	 * Description of the property TempMin.
	 */
	private double TempMin = 0;
	
	public Weather() {
	}

	/**
	 * Description of the method getTempMin.
	 * @return 
	 */
	public double getTempMin() {
		return TempMin;
	}

	/**
	 * Description of the method getNome.
	 * @return 
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * Description of the method getPressione.
	 * @return 
	 */
	public double getPressione() {
		return Pressione;
	}

	/**
	 * Description of the method getTempMax.
	 * @return 
	 */
	public double getTempMax() {
		return TempMax;
	}

	/**
	 * Description of the method getTempPercepita.
	 * @return 
	 */
	public double getTempPercepita() {
		return TempPercepita;
	}
	
	public int getGiorno() {
		return giorno;
	}
	
	public void setNome(String nome) {
		this.Nome=nome;
	}
	
	public void setGiorno(int giorno) {
		this.giorno=giorno;
	}
	
	public void setTempMin(double tempMin) {
		this.TempMin=tempMin;
	}

	public void setTempMax(double tempMax) {
		this.TempMax=tempMax;
	}
	
	public void setPressione(double pressione) {
		this.Pressione=pressione;
	}
	
	public void setTemp(double temp) {
		this.TempPercepita=temp;
	}
}
