package service;
import org.springframework.web.bind.annotation.RestController;
import errors.ErrorException;
import errors.FileIsEmpty;
import errors.FileNotFound;
import filtri.ErrorFilter;
import filtri.StatsRequest;
import model.Weather;
import model.WeatherCollection;
import parser.JsonParser;
import model.ModelError;

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

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Classe che contiene e gestisce tutte le rotte del servizio.
 * 
 * @author Massimo Mecarelli
 * @author Marco Pasquale Martino
 */

@RestController
public class WeatherService {

	private static String key;
	private static double[] lat=new double[5];
	private static double[] lon=new double[5];

	/**
	 * Costruttore della classe il quale imposta la key e latitudine e longitudine di alcune città predefinite.
	 * Questi dati sono memorizzati in dei file, per rendere comodee veloci le modifiche future. 
	 */
	public WeatherService(){
		try {
			int i=0;
			JSONObject jobject;
			key=Files.readString(Path.of("src/main/resources/key.txt"));
			File file=new File("src/main/resources/city.json");
			Scanner file_in=new Scanner(new BufferedReader(new FileReader(file)));
			while(file_in.hasNextLine()) {
				jobject=new JSONObject(file_in.nextLine());
				lat[i]=jobject.getDouble("lat");
				lon[i]=jobject.getDouble("lon");
				i++;
			}
			if(lat.length!=lon.length) {
				System.out.println("Errore lettura file city.txt");
				System.exit(1);
			}
		}catch(IOException | JSONException e) {
			e.getStackTrace();
		}
	}

	/**
	* Metodo che visualizza tutto il file con le history
	*/
	/*@GetMapping(value="/alldata")
	public JSONObject all(){
		Utility utils = new Utility();
		return utils.readAllFile();
	}*/
	
	/**
	 * Metodo che, ogni tre ore, legge automaticamente il meteo attuale di alcune città predefinite e le salva all'interno del file weather.json.
	 * @return sugg lettura ottenuta tramite l'update.
	 */
	@Scheduled(fixedRate=10800000)
	@RequestMapping(value="/update")
	public Vector<Weather> updateSugg(){
		Vector<Weather> sugg=new Vector<Weather>();
		JsonParser parser = new JsonParser();
		System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm").format(LocalDateTime.now()));
		for(int i=0;i<lat.length;i++) {
			sugg.addAll(parser.readAPI("http://api.openweathermap.org/data/2.5/forecast?lat="+lat[i]+"&lon="+lon[i]+
					"&appid="+key+"&cnt="+40+"&units=metric&lang=it"));
		}
		return sugg;
	}
	
	/**
	 * Metodo che, tramite latitudine e longitudine, manda una richiesta a OpenWeather.
	 * Una volta effettuata la richiesta, i dati ricevuti, se non presenti, saranno memorizzati all'interno del file weather.json il quale funge da storico.
	 * Tramite il parametro "cnt", sarà possibile scegliere quante letture visualizzare (con un intervallo di 3 ore tra un dato e l'altro).
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt quantità di dati da richiedere al server
	 * @return lettura dati ottenuti dal server
	 */
	@RequestMapping(value="/weather", method=RequestMethod.GET)
	public Vector<Weather> getWeather(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt) {
		
		JsonParser parser = new JsonParser(); //creo un JsonParser
		
		//leggo dall'API, passando il tipo di Json e l'url al parser
		
		return parser.readAPI("http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+
				"&appid="+key+"&cnt="+(cnt*8)+"&units=metric&lang=it");
	}
	
	/**
	 * Metodo che restituisce le statistiche generali di una determinata città, in un lasso di tempo deciso dall'utente.
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt quantità di dati su cui generale le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @return statistiche calcolate
	 */
	@RequestMapping(value="/stats", method=RequestMethod.GET)
	public Vector<Weather> getStats(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1")int cnt, HttpServletResponse response) {
		
		StatsRequest stat= new StatsRequest();
		return stat.getAll(lat, lon, cnt, response);
	}
	
	/**
	 * Richiedere statistiche relative alla pressione
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt quantità di dati su cui generale le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @return statistiche calcolate
	 */
	@RequestMapping(value="/stats/pressione", method=RequestMethod.GET)
	public StatsRequest getPres(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="giornaliero")String cnt, HttpServletResponse response) {
	
		StatsRequest stat = new StatsRequest();
		if (cnt.equals("giornaliero")){
			try {
				stat.setPressMassima(lat, lon, 1, response);
				stat.setPressMinima(lat, lon, 1, response);
			} catch (FileNotFound | FileIsEmpty e) { e.printStackTrace();}
			return stat;
		}
		else if (cnt.equals("settimanale")){
			try {
				stat.setPressMassima(lat, lon, 7, response);
				stat.setPressMinima(lat, lon, 7, response);
			} catch (FileNotFound | FileIsEmpty e) { e.printStackTrace();}
		}
		else if (cnt.equals("mensile")){
			try {
				stat.setPressMassima(lat, lon, 30, response);
				stat.setPressMinima(lat, lon, 30, response);
			}catch (FileNotFound | FileIsEmpty e) { e.printStackTrace();}
			return stat;
		}
		else System.out.println("Periodo non valido");
		return null;
	}
	
	/**
	 * Richiede statistiche relative alla temperatura massima e minima in un determinato lasso di tempo
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt quantità di dati su cui generale le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @return statistiche calcolate
	 */
	@ExceptionHandler({ FileNotFound.class })
	@RequestMapping(value="/stats/temperature/maxmin", method=RequestMethod.GET)
	public StatsRequest getTemMinMax(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="giornaliero")String cnt, HttpServletResponse response)
		throws FileIsEmpty {
		
		StatsRequest stat = new StatsRequest();
		if (cnt.equals("giornaliero")){
			stat.getMin(lat, lon, 1);
			stat.getMax(lat, lon, 1);
			return stat;
		}
		else if (cnt.equals("settimanale")){
			stat.getMin(lat, lon, 7);
			stat.getMax(lat, lon, 7);
			return stat;
		}
		else if (cnt.equals("mensile")){
			stat.getMin(lat, lon, 30);
			stat.getMax(lat, lon, 30);
			return stat;
		}
		else System.out.println("Periodo non valido");
		return null;
	}

	/*
	* Metodo che restituisce la temperatura media e la media percepita
	*/
	@ExceptionHandler({ FileNotFound.class })
	@RequestMapping(value="/stats/temperature/medie", method=RequestMethod.GET)
	public WeatherCollection getTemMedie(
			@RequestParam(value="lat") double lat, 
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="giornaliero")String cnt, HttpServletResponse response){
		
		StatsRequest stat = new StatsRequest();
		try {
			if (cnt.equals("giornaliero"))
				return stat.getTemperatureAvrg(lat, lon, 1, response);
			else if (cnt.equals("settimanale"))
				return stat.getTemperatureAvrg(lat, lon, 7, response);
			else if (cnt.equals("mensile"))
				return stat.getTemperatureAvrg(lat, lon, 30, response);
			else System.out.println("Periodo non valido");
		} catch (FileNotFound | FileIsEmpty e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt quantità di dati su cui calcolare l'errore
	 * @param err percentuale massima di errore da mostrare
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @return dati riguardanti la percentuale di errore
	 * @throws FileNotFound eccezione per gestire eventuali errori per la mancanza di file
	 */
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public Vector<ModelError> getError(
			@RequestParam(value="lat") double lat,
			@RequestParam(value="lon") double lon,
			@RequestParam(value="cnt", defaultValue="1") int cnt,
			@RequestParam(value="err") double err, HttpServletResponse response) throws FileNotFound{
		ErrorFilter errorfilter=new ErrorFilter();
		Vector<ModelError> error=errorfilter.getTempError(lat, lon, cnt, err, response);
		if(error.isEmpty()) throw new ErrorException(response,err);
		return error;
	}
}
	
