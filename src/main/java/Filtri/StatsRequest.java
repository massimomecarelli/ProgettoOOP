package Filtri;

import java.util.Vector;
import org.json.*;

import Parser.JsonParser;
import Service.*;
import Filtri.StatsAverageImpl;
import model.*;



/**
 * @author Massimo
 * 
 * Descrizione di StatsRequest.
 */
public class StatsRequest {
	

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
	 */
	public Vector<Weather> getAll(double lat, double lon, int cnt) {
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt);
		return weather;
	}

	/**
	 * Descrizione del metodo getTemperature.
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 */
	public WeatherCollection getTemperatureAvrg(double lat, double lon, int cnt) {
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt);
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

	public Vector<WeatherTemp> getTemperature(double lat, double lon, int cnt) {
		//aggiunta di un vettore WeatherCollection per prendere valori di media
		Vector<WeatherTemp> weatherTemp = new Vector<WeatherTemp>();
		Vector<Weather> weather = new Vector<Weather>();
		weatherTemp.add(null);
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt);
		for (int i=0; i<weather.size(); i++){
			weatherTemp.get(i).setter(weather.get(i));
		}
		return weatherTemp;
	}
	
	
	/**
	 * Descrizione del metodo getPress.
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 */
	public Vector<WeatherPress> getPress(double lat, double lon, int cnt) {
		Vector<Weather> weather = new Vector<Weather>();
		Vector<WeatherPress> weatherPress = new Vector<WeatherPress>();
		weatherPress.add(null);
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt);
		for(int i=0; i<weather.size(); i++){
			weatherPress.get(i).setter(weather.get(i));
		}
		return weatherPress;
	}


	public JSONObject Suggested(){
		
	}

}