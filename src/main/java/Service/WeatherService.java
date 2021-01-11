package Service;
import model.*;
import Filtri.*;
import Parser.*;

import org.springframework.web.bind.annotation.RestController;
import Parser.JsonParser;
import errors.FileNotFound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Description of WeatherService.
 * 
 * @author Massimo
 */

@RestController
public class WeatherService {

	private static String key;
	private static double[] lat=new double[5];
	private static double[] lon=new double[5];

	/**
	 * Il costruttore.
	 */
	public WeatherService() {
		try {
			int i=0;
			key=Files.readString(Path.of("src/main/resources/key.txt"));
			File file=new File("src/main/resources/city.txt");
			Scanner file_in=new Scanner(new BufferedReader(new FileReader(file)));
			while(file_in.hasNextLine()) {
				String str=file_in.nextLine();
				if(str.indexOf(",")!=-1) {
					lat[i]=Double.parseDouble(str.substring(0,str.indexOf(",")));
					lon[i]=Double.parseDouble(str.substring(str.indexOf(",")+1,str.length()));
					i++;
				}
			}
			if(lat.length!=lon.length) {
				System.out.println("Errore lettura file city.txt");
				System.exit(1);
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
	}

	/**
	* Metodo che visualizza tutto il file con le history
	*/
	@GetMapping(value="/alldata")
	public JSONObject all(){
		Utility utils = new Utility();
		return utils.readAllFile();
	}
	
	/**
	 * 
	 */
	@Scheduled(fixedRate=60000)
	@RequestMapping(value="/update")
	public Vector<Weather> updateSugg(){
		Vector<Weather> sugg=new Vector<Weather>();
		JsonParser parser = new JsonParser();
		System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm").format(LocalDateTime.now()));
		for(int i=0;i<lat.length;i++) {
			sugg.addAll(parser.readAPI("http://api.openweathermap.org/data/2.5/forecast?lat="+lat[i]+"&lon="+lon[i]+
					"&appid="+key+"&cnt="+1+"&units=metric&lang=it"));
		}
		return sugg;
	}
	
	/**
	* Metodo che gestisce una chiamata alla home, che suggerisce latitudine e longitudine di alcune città
	*/
	@GetMapping(value="/")
	public JSONObject home(){
		StatsRequest stats = new StatsRequest();
		return stats.Suggested();
	}

	/**
	 * 
	 */
	@RequestMapping(value="/weather", method=RequestMethod.GET)
	public Vector<Weather> getWeather(
			@RequestParam(value="lat", defaultValue="13.51") double lat, 
			@RequestParam(value="lon", defaultValue="43.60") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		
		JsonParser parser = new JsonParser(); //creo un JsonParser
		
		//leggo dall'API, passando il tipo di Json e l'url al parser
		
		return parser.readAPI("http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+
				"&appid="+key+"&cnt="+cnt+"&units=metric&lang=it");
	}
	
	//statistiche riguardo previsioni azzeccate in generale 
	@RequestMapping(value="/stats", method=RequestMethod.GET)
	public Vector<Weather> getStats(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt, HttpServletResponse response) {
		
		StatsRequest stat= new StatsRequest();
		return stat.getAll(lat, lon, cnt, response);
	}
	
	//richiedere statistiche di un solo dato specifico
	@RequestMapping(value="/stats/pressione", method=RequestMethod.GET)
	public Vector<WeatherPress> getPres(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt, HttpServletResponse response) {
	
		StatsRequest stat = new StatsRequest();
		return stat.getPress(lat, lon, cnt, response);
	}
	
	@ExceptionHandler({ FileNotFound.class })
	@RequestMapping(value="/stats/temperature", method=RequestMethod.GET)
	public Vector<WeatherTemp> getTem(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="giornaliero")String cnt, HttpServletResponse response){
		StatsRequest stat = new StatsRequest();
		if (cnt.equals("giornaliero"))
			return stat.getTemperature(lat, lon, 1,response);
		else if (cnt.equals("settimanale"))
			return stat.getTemperature(lat, lon, 7,response);
		else if (cnt.equals("mensile"))
			return stat.getTemperature(lat, lon, 30,response);
		else System.out.println("Periodo non valido");
		return null;
	}
	
}