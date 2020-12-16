/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package Filtri;

import java.util.Vector;
import model.WeatherCollection;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of statsRequest.
 * 
 * @author Massimo
 */
public class statsRequest {
	/**
	 * Description of the property .
	 */
	public int Integer;

	/**
	 * Description of the property weatherCollections.
	 */
	public Vector<WeatherCollection> weatherCollections = new Vector<WeatherCollection>();

	// Start of user code (user defined attributes for statsRequest)

	// End of user code

	/**
	 * The constructor.
	 */
	public statsRequest() {
		// Start of user code constructor for statsRequest)
		super();
		// End of user code
	}

	/**
	 * Description of the method getStats.
	 * @param nome 
	 * @param periodo 
	 */
	public void getStats(String nome, int periodo) {
		// Start of user code for method getStats
		// End of user code
	}

	/**
	 * Description of the method getStats.
	 * @param citta 
	 * @param periodo 
	 * @param stat 
	 */
	public void getStats(String citta, int periodo, String stat) {
		// Start of user code for method getStats
		// End of user code
	}

	// Start of user code (user defined methods for statsRequest)

	// End of user code
	/**
	 * Returns .
	 * @return  
	 */
	public int get() {
		return this.Integer;
	}

	/**
	 * Sets a value to attribute . 
	 * @param new 
	 */
	public void set(int newInteger) {
	    this.Integer = newInteger;
	}

	/**
	 * Returns weatherCollections.
	 * @return weatherCollections 
	 */
	public Vector<WeatherCollection> getWeatherCollections() {
		return this.weatherCollections;
	}

}
