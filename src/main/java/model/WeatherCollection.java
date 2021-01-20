package model;

/**
 * Classe che contiene i parametri riguardanti le temperature medie
 */
public class WeatherCollection {

	/**
	 * Attributo che indica la temperatura media.
	 */
	private double TempMedia;
	/**
	 * Attributo che indica la temperatura media percepita.
	 */
	private double TempMediaPerc;
	/**
	 * Attributo che indica il nome della città.
	 */
	private String nome;
	/**
	 * Costruttore nullo.
	 */
	public WeatherCollection() {
	}

	/**
	 * Metodo che assegna un valore alla temperatura media.
	 * @param media : temperatura media.
	 */
	public void setTempMedia(double media) {
		this.TempMedia=media;
	}
	
	/**
	 * Metodo che assegna un valore alla temperatura media percepita.
	 * @param media : temperatura media percepita.
	 */
	public void setTempMediaPerc(double media) {
		this.TempMediaPerc=media;
	}
	
	/**
	 * Metodo che assegna un valore al nome.
	 * @param nome : nome della città.
	 */
	public void setNome(String nome) {
		this.nome=nome;
	}

	/**
	 * Metodo che restituisce la temperatura media.
	 * @return TempMedia : temperatura media.
	 */
	public double getTemMedia() {
		return this.TempMedia;
	}
	
	/**
	 * Metodo che restituisce il nome della citta.
	 * @return nome : nome della città.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo che restituisce la temperatura media percepita.
	 * @return TempMediaPerc : temperatura media percepita.
	 */
	public double getTemMediaPerc() {
		return this.TempMediaPerc;
	}

}
