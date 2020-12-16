/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package model;

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
	public HashSet<Controller> controllers = new HashSet<Controller>();

	/**
	 * Description of the property weathers.
	 */
	public HashSet<Weather> weathers = new HashSet<Weather>();

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
	public void setWeather(Object ArrayList<Weather>) {
		// Start of user code for method setWeather
		// End of user code
	}

	// Start of user code (user defined methods for WeatherCollection)

	// End of user code
	/**
	 * Returns controllers.
	 * @return controllers 
	 */
	public HashSet<Controller> getControllers() {
		return this.controllers;
	}

	/**
	 * Returns weathers.
	 * @return weathers 
	 */
	public HashSet<Weather> getWeathers() {
		return this.weathers;
	}

}
