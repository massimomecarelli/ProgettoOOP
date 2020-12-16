/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package model;

import java.util.Vector;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of WeatherCollection.
 * 
 * @author Massimo
 */
public class WeatherCollection {
	/**
	 * Description of the property controllers.
	 */
	//public Vector<Controller> controllers = new Vector<Controller>();

	/**
	 * Description of the property weathers.
	 */
	public Vector<Weather> weathers = new Vector<Weather>();

	// Start of user code (user defined attributes for WeatherCollection)

	// End of user code

	/**
	 * The constructor.
	 */
	public WeatherCollection() {
		// Start of user code constructor for WeatherCollection)
		super();
		// End of user code
	}

	/**
	 * Description of the method setWeather.
	 * @param ArrayList<Weather> 
	 */
	public void setWeather(Vector<Weather> wet) {
		// Start of user code for method setWeather
		// End of user code
	}

	// Start of user code (user defined methods for WeatherCollection)

	// End of user code
	/**
	 * Returns controllers.
	 * @return controllers 
	 */

	/**
	 * Returns weathers.
	 * @return weathers 
	 */
	public Vector<Weather> getWeathers() {
		return this.weathers;
	}

}
