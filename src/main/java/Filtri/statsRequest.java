/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package Filtri;

import java.util.Vector;

import Parser.JsonParser;
import model.WeatherCollection;
import Service.*;
import model.Weather;



/**
 * Description of statsRequest.
 * 
 * @author Massimo
 */
public class statsRequest {
	

	/**
	 * The constructor.
	 */
	public statsRequest() {
		
	}

	/**
	 * Description of the method getAll.
	 * @param lat
	 * @param lon 
	 * @param cnt 
	 */
	public Vector<Weather> getAll(String key, double lat, double lon, int cnt) {
		Vector<Weather> weather = new Vector<Weather>;
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt);
		return weather;
	}

	/**
	 * Description of the method getDato.
	 * @param lat 
	 * @param lon 
	 * @param cnt 
	 * @param dato
	 */
	public void getDato(double lat, double lon, int cnt) {
		JsonParser parser = new JsonParser();
		
		parser.readFile(lat, lon, cnt);
	}

}
