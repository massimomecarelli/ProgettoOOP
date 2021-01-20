/**
 * 
 */
package model;

import filtri.WeatherFilteredArchive;

/**
 * Classe che implementa l'interfaccia WeatherFilteredArchive
 * per permettere l'estrazione dei soli parametri riguardanti le temperature
 * da un archivio di tipo Weather.
 * @implements WeatherFilteredArchive
 */
public class WeatherTemp implements WeatherFilteredArchive {

	private double TempMax;
	private double TempMin;
	private double TempPerc;
	
	public WeatherTemp() {
		
	}
	
	public WeatherTemp(Weather weather) {
		setter(weather);
	}
	
	@Override
	public void setter(Weather weather) {
		TempMax=weather.getTempMax();
		TempMin=weather.getTempMin();
		TempPerc=weather.getTempPercepita();
	}
	
	public double getTMax() {
		return TempMax;
	}
	
	public double getTMin() {
		return TempMin;
	}
	
	public double getTPerc() {
		return TempPerc;
	}
}
