package model;

import Filtri.WeatherFilteredArchive;

 /**
 *	@author Massimo
 *
 * Classe che implementa l'interfaccia WeatherFilteredArchive
 * per permettere l'estrazione dei soli parametri riguardanti la pressione
 * da un archivio di tipo Weather
 */
public class WeatherPress implements WeatherFilteredArchive {

	private double Pressione;

	public WeatherPress() {}

	@Override
	public void setter(Weather weather) {
		Pressione=weather.getPressione();
	}
	
	public double getPressione() {
		return Pressione;
	}

}
