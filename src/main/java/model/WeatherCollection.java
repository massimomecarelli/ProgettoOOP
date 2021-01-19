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
	private String nome;
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
	
	public void setNome(String nome) {
		this.nome=nome;
	}

	/**
	 * @return TempMedia 
	 */
	public double getTemMedia() {
		return this.TempMedia;
	}
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * @return TempMediaPerc
	 */
	public double getTemMediaPerc() {
		return this.TempMediaPerc;
	}

}
