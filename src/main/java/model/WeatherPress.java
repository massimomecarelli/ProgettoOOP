package model;

import filtri.WeatherFilteredArchive;

 /**
 * Classe che implementa l'interfaccia WeatherFilteredArchive
 * per permettere l'estrazione dei soli parametri riguardanti la pressione
 * da un archivio di tipo Weather.
 */
public class WeatherPress implements WeatherFilteredArchive {

	/**
	 * Attributo che indica la pressione.
	 */
	private int Pressione;

	/**
	 * Costruttore nullo.
	 */
	public WeatherPress() {}
	
	/**
	 * Costruttore che imposta i valori della pressione.
	 * @param weather : oggetto di tipo Weather contente il valore della pressione.
	 */
	public WeatherPress(Weather weather) {
		setter(weather);
	}

	/**
	 * Metodo per impostare i valori della pressione.
	 * @param weather : oggetto di tipo Weather contente il valore della pressione.
	 */
	@Override
	public void setter(Weather weather) {
		Pressione=weather.getPressione();
	}
	
	/**
	 * Metodo che restituisce la pressione.
	 * @return Pressione : pressione.
	 */
	public int getPressione() {
		return Pressione;
	}

}
