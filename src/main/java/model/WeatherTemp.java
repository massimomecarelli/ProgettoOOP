/**
 * 
 */
package model;

import Filtri.WeatherFilteredArchive;

/**
 * @author Massimo
 *
 *	Classe che implementa l'interfaccia WeatherFilteredArchive
 * per permettere l'estrazione dei soli parametri riguardanti le temperature
 * da un archivio di tipo Weather
 */
public class WeatherTemp implements WeatherFilteredArchive {

	private double TempMax;
	private double TempMin;
	private double TempPerc;
	
	@Override
	public void setter(Weather weather) {
		TempMax=weather.getTempMax();
		TempMax=weather.getTempMin();
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
