/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package Service;
import model.*;
import Filtri.*;

import org.springframework.web.bind.annotation.RestController;
import Parser.JsonParser;

import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;



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


	/**
	 * The constructor.
	 */
	public WeatherService() {}

	/**
	 * Description of the method updateWeather.
	 */
	@GetMapping("/weather")
	public Vector<Weather> getWeather(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		JsonParser parser = new JsonParser(); //creo un JsonParser
		
		//leggo dall'API, passando il tipo di Json e l'url al parser
		parser.readAPI("http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+
				       "&appid="+key+"&cnt="+cnt+"&units=metric&lang=it");
		//parser.readFile(lat, lon);
		//ricevo in uscita i dati per quella determinata città
		return parser.getWeather();
	}
	
	//statistiche riguardo previsioni azzeccate in generale 
	@GetMapping("/stats")
	public statsRequest getStats(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		
		statsRequest stat= new statsRequest();
		return stat.getAll(cnt, lat, lon);
	}
	
	//richiedere statistiche di un solo dato specifico
	@GetMapping("/stats/pressione")
	public statsRequest getPres(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
	
		statsRequest stat = new statsRequest();
		return stat.getPress(cnt, lat, lon);
	}
	
	@GetMapping("/stats/temperature")
	public statsRequest getTem(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
	
		statsRequest stat = new statsRequest();
		return stat.getTemperature(cnt, lat, lon);
	}
	
}
