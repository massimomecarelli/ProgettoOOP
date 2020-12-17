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
	private static boolean obj=true;


	/**
	 * The constructor.
	 */
	public WeatherService() {}

	/**
	 * Description of the method updateWeather.
	 */
	@GetMapping("/weather")
	public Weather getWeather(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		if (cnt!=1) obj=false; //caso in cui si vogliano ricevere più giorni
								//quindi si richiede un JSONArray invece di un JSONObject
		JsonParser parser = new JsonParser(); //creo un JsonParser
		
		//leggo dall'API, passando il tipo di Json e l'url al parser
		parser.readAPI(obj,"http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+
				"&appid="+key+"&cnt="+cnt+"&units=metric&lang=it");
		//ricevo in uscita i dati per quella determinata città
		
		return parser.getWeather();
	}
	
	//statistiche riguardo previsioni azzeccate in generale 
	@GetMapping("/stats")
	public StatsRequest getStats(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		
		StatsRequest stat= new StatsRequest();
		return stat.getAll(lat, lon, cnt);
	}
	
	//richiedere statistiche di un solo dato specifico
	@GetMapping("/stats/pressione")
	public StatsRequest getPres(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
	
		StatsRequest stat = new StatsRequest();
		return stat.getPress(lat, lon, cnt);
	}
	
	@GetMapping("/stats/temperature")
	public StatsRequest getTem(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="giornaliero")String cnt) {
		StatsRequest stat = new StatsRequest();
		if (cnt.equals("giornaliero"))
			return stat.getTemperature(lat, lon, 1);
		else if (cnt.equals("settimanale"))
			return stat.getTemperature(lat, lon, 7);
		else if (cnt.equals("mensile"))
			return stat.getTemperature(lat, lon, 30);
	}
	
}