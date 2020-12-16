/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package Service;


import org.springframework.web.bind.annotation.RestController;
import Parser.JsonParser;

import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.WeatherCollection;
import model.Weather;

/**
 * Description of WeatherService.
 * 
 * @author Massimo
 */
@RestController
public class WeatherService {
	/**
	 * Description of the property weathers.
	 */
	public Vector<WeatherCollection> weathers = new Vector<WeatherCollection>();

	private static String key="954cc342074e04241297f65762f40232";
	private double lat;
	private double lon;
	private int cnt;


	/**
	 * The constructor.
	 */
	public WeatherService() {}

	/**
	 * Description of the method updateWeather.
	 */
	@GetMapping("/weather")
	public Vector<Weather> getWeather(@RequestParam(value="lat") double latitudine, 
			@RequestParam(value="lon") double longitudine,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		this.lat=latitudine;
		this.lon=longitudine;
		this.cnt=cnt;
		
		JsonParser parser = new JsonParser(); //creo un JsonParser
		
		//leggo dall'API, passando il tipo di Json e l'url al parser
		parser.readAPI("http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+"&appid="+key+"&cnt="+cnt+"&units=metric&lang=it");
		//ricevo in uscita i dati per quella determinata città
		
		return parser.getWeather();
	}

}
