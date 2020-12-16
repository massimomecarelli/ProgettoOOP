/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package Service;
import org.springframework.web.bind.annotation.RestController;

import Parser.JsonParser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.WeatherCollection;

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
	public HashSet<WeatherCollection> weathers = new HashSet<WeatherCollection>();

	
	public static boolean obj=true;
	public static double lat;
	public static double lon;
	public static int cnt;


	/**
	 * The constructor.
	 */
	public WeatherService() {}

	/**
	 * Description of the method updateWeather.
	 */
	@GetMapping("/weather")
	public void getWeather(@RequestParam(value="lat") double latitudine, 
			@RequestParam(value="lon") double longitudine,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		this.lat=latitudine;
		this.lon=longitudine;
		this.cnt=cnt;
		if (cnt!=1) obj=false; //caso in cui si vogliano ricevere più giorni
								//quindi si richiede un JSONArray invece di un JSONObject
		JsonParser parser = new JsonParser(); //creo un JsonParser
		//leggo dall'API, passando il tipo di Json e l'url al parser
		parser.readFile(obj,
				"api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+"&appid=954cc342074e04241297f65762f40232&cnt="+cnt+"&units=metric&lang=it");
		//ricevo in uscita i dati per quella determinata città
		parser.getWeather();
	}

}
