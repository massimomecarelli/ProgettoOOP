package filtri;

import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import errors.*;
import model.Weather;
import model.WeatherCollection;
import model.WeatherPress;
import model.WeatherTemp;
import parser.JsonParser;



/**
 * @author Massimo Mecarelli
 * 
 * Descrizione di StatsRequest.
 */
public class StatsRequest {
	
	private double Max=0;
	private double Min=0;

	/**
	 * Costruttore
	 */
	public StatsRequest() {
		
	}

	/**
	 * Descrizione del metodo getAll.
	 * @param lat
	 * @param lon 
	 * @param cnt 
	 * @throws FileNotFound 
	 */
	public Vector<Weather> getAll(double lat, double lon, int cnt, HttpServletResponse response) {
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		try {
			weather = parser.readFile(lat, lon, cnt, response);
		} catch (FileNotFound e) {}
		return weather;
	}

	/**
	 * Metodo che calcola la media di temperatura degli ultimi giorni (inserito dall'utente)
	 *  ritornando un oggetto che possiede come parametri la temperatura media e la media percepita
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 * @return WeatherCollection
	 * @throws FileNotFound 
	 */
	public WeatherCollection getTemperatureAvrg(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt, response);
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!", response);
		StatsAverageImpl average=new StatsAverageImpl();
		WeatherCollection collection = new WeatherCollection();
		Vector<Double> tTot = new Vector<Double>();
		Vector<Double> tPerc = new Vector<Double>();
		
		try {
		for (int i=0; i<cnt; i++) {
			tTot.add(weather.get(i).getTempMax());
			tTot.add(weather.get(i).getTempMin());
			tPerc.add(weather.get(i).getTempPercepita());
		}
		collection.setTempMedia(average.getMedia(tTot));
		collection.setTempMediaPerc(average.getMedia(tPerc));
		}catch(Exception e) {e.printStackTrace();}
		return collection;
	}

	/**
	 * Metodo che prende il valore massimo delle temperature nei giorni precedenti (inserito dall'utente)
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 * @throws FileNotFound 
	 */
	public void setTemperatureMax(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		//aggiunta di un vettore WeatherCollection per prendere valori di media
		Vector<WeatherTemp> weatherTemp = new Vector<WeatherTemp>();
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt, response);
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!",response);
		for (int i=0; i<weather.size(); i++){
			weatherTemp.get(i).setter(weather.get(i));
		}
		//trovo il valore massimo
		StatsMaxMin max = new StatsMaxMin();
		max.setMax(weatherTemp, 't');
		this.Max=max.getMax();
	}

	/**
	 * Metodo che prende il valore minimo delle temperature nei giorni precedenti (inserito dall'utente)
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 * @throws FileNotFound 
	 */
	public void setTemperatureMin(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		//aggiunta di un vettore WeatherCollection per prendere valori di media
		Vector<WeatherTemp> weatherTemp = new Vector<WeatherTemp>();
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt, response);
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!",response);
		for (int i=0; i<weather.size(); i++){
			weatherTemp.get(i).setter(weather.get(i));
		}
		//trovo il valore minimo
		StatsMaxMin min = new StatsMaxMin();
		min.setMin(weatherTemp, 't');
		this.Min = min.getMin();
	}
	
	
	/**
	 * Metodo che prende il valore massimo delle pressioni nei giorni precedenti (inserito dall'utente)
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 * @throws FileNotFound 
	 */
	public void setPressMassima(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		Vector<Weather> weather = new Vector<Weather>();
		Vector<WeatherPress> weatherPress = new Vector<WeatherPress>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt, response);
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!",response);
		for(int i=0; i<weather.size(); i++){
			weatherPress.get(i).setter(weather.get(i));
		}
		//trovo il valore massimo
		StatsMaxMin max = new StatsMaxMin();
		max.setMin(weatherPress);
		this.Max=max.getMax();
	}


	/**
	 * Metodo che prende il valore minimo delle pressioni nei giorni precedenti (inserito dall'utente)
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 * @throws FileNotFound 
	 */
	public void setPressMinima(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		Vector<Weather> weather = new Vector<Weather>();
		Vector<WeatherPress> weatherPress = new Vector<WeatherPress>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt, response);
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!",response);
		for(int i=0; i<weather.size(); i++){
			weatherPress.get(i).setter(weather.get(i));
		}
		//trovo il valore minimo
		StatsMaxMin min = new StatsMaxMin();
		min.setMin(weatherPress);
		this.Min=min.getMin();
	}

	/**
	 * Metodo che restituisce il valore minimo delle pressioni nei giorni precedenti (inserito dall'utente)
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 */
	public double getMin(double lat, double lon, int cnt){
		return Min;
	}

	/**
	 * Metodo che restituisce il valore massimo delle pressioni nei giorni precedenti (inserito dall'utente)
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 */
	public double getMax(double lat, double lon, int cnt){
		return Max;
	}

}