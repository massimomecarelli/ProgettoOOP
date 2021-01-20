package model;

import filtri.WeatherFilteredArchive;

 /**
 * Classe che implementa l'interfaccia WeatherFilteredArchive
 * per permettere l'estrazione dei soli parametri riguardanti la pressione
 * da un archivio di tipo Weather.
 */
public class WeatherPress implements WeatherFilteredArchive {

	private int Pressione;

	public WeatherPress() {}
	
	public WeatherPress(Weather weather) {
		setter(weather);
	}

	@Override
	public void setter(Weather weather) {
		Pressione=weather.getPressione();
	}
	
	public int getPressione() {
		return Pressione;
	}

}
