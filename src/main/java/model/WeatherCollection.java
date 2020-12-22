package model;

/**
 * @author Massimo
 * 
 * Classe che contiene i parametri riguardanti le temperature medie
 */
public class WeatherCollection {

	/**
	 * Attributi
	 */
	private double TempMedia;
	private double TempMediaPerc;
	/**
	 * Costruttore
	 */
	public WeatherCollection() {
		super();
	}

	/**
	 * @param media
	 */
	public void setTempMedia(double media) {
		this.TempMedia=media;
	}
	
	public void setTempMediaPerc(double media) {
		this.TempMediaPerc=media;
	}

	/**
	 * @return TempMedia 
	 */
	public double getTemMedia() {
		return this.TempMedia;
	}
	
	
	/**
	 * @return TempMediaPerc
	 */
	public double getTemMediaPerc() {
		return this.TempMediaPerc;
	}

}
