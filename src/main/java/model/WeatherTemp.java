package model;

import filtri.WeatherFilteredArchive;

/**
 * Classe che implementa l'interfaccia WeatherFilteredArchive
 * per permettere l'estrazione dei soli parametri riguardanti le temperature
 * da un archivio di tipo Weather.
 */
public class WeatherTemp implements WeatherFilteredArchive {

	/**
	 * Attributo che rappresenta la temperatura massima.
	 */
	private double TempMax;
	/**
	 * Attributo che rappresenta la temperatura minima.
	 */
	private double TempMin;
	/**
	 * Attributo che rappresenta la temperatura percepità.
	 */
	private double TempPerc;
	
	/**
	 * Costruttore vuoto
	 */
	public WeatherTemp() {
		
	}
	
	/**
	 * Costruttore per inizializzare gli attributi della classe.
	 * @param weather : parametro contenente i dati da inserire.
	 */
	public WeatherTemp(Weather weather) {
		setter(weather);
	}
	
	/**
	 * Metodo che inizializza gli attributi della classe.
	 */
	@Override
	public void setter(Weather weather) {
		TempMax=weather.getTempMax();
		TempMin=weather.getTempMin();
		TempPerc=weather.getTempPercepita();
	}
	
	/**
	 * Metodo che restiuisce la temperatura massima.
	 * @return TempMax : temperatura massima.
	 */
	public double getTMax() {
		return TempMax;
	}
	
	/**
	 * Metodo che restituisce la temperatura minima.
	 * @return TempMin : temperatura minima.
	 */
	public double getTMin() {
		return TempMin;
	}
	
	/**
	 * Metodo che restituisce la temperatura percepita.
	 * @return TempPerc : temperatura percepita.
	 */
	public double getTPerc() {
		return TempPerc;
	}
}
