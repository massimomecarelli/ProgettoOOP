/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package Service;
import model.*;
import Filtri.*;

import org.springframework.web.bind.annotation.RestController;
import Parser.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
	private static String key;
	private static boolean obj=true;


	/**
	 * Costruttore che si occupa di leggere la key di OpenWeather da file.
	 */
	public WeatherService() {
		try {
			key=Files.readString(Path.of("src/main/resources/key.txt"));
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	/*@PostMapping("/weatherP")
	public Vector<Weather> getWeather(@RequestBody Request request) {
		
		JsonParser parser = new JsonParser(); //creo un JsonParser
		
		//leggo dall'API, passando il tipo di Json e l'url al parser
		parser.readAPI("http://api.openweathermap.org/data/2.5/forecast?lat="+request.getLat()+"&lon="+request.getLon()+
				"&appid="+key+"&cnt="+request.getCnt()+"&units=metric&lang=it");
		//ricevo in uscita i dati per quella determinata città
		
		return parser.getWeather();
	}*/

	/**
	 * Description of the method updateWeather.
	 */
	@GetMapping("/weather")
	public Vector<Weather> getWeather(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		
		JsonParser parser = new JsonParser(); //creo un JsonParser
		String url="http://api.openweather.org/data/2.5/forecast?lat="+lat+"&lon="+lon+"&appid="+key+"&cnt="+cnt+"&units=metric&lang=it");
		//leggo dall'API, passando il tipo di Json e l'url al parser
		parser.readAPI(url);
		//ricevo in uscita i dati per quella determinata città
		return parser.readAPI(url);
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
	public WeatherCollection getTem(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="giornaliero")String cnt) {
		StatsRequest stat = new StatsRequest();
		if (cnt.equals("giornaliero"))
			return stat.getTemperature(lat, lon, 1);
		else if (cnt.equals("settimanale"))
			return stat.getTemperature(lat, lon, 7);
		return stat.getTemperature(lat, lon, 30);
	}
	
}