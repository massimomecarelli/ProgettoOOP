package filtri;

import model.Weather;

/**
 *	@author Massimo Mecarelli
 *
 * Interfaccia che permette di filtrare un archivio di tipo Weather.
 */

public interface WeatherFilteredArchive{
	/**
	 * Metodo ausiliario per impostare dei dati.
	 * @param weather : oggetto di tipo weather contenente diversi tipi di dati.
	 */
	public void setter(Weather weather);
}