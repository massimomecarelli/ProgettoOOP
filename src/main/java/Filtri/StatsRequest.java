package Filtri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Parser.JsonParser;
import Service.*;
import errors.FileNotFound;
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
	 * Descrizione del metodo getTemperature.
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 * @throws FileNotFound 
	 */
	public WeatherCollection getTemperatureAvrg(double lat, double lon, int cnt, HttpServletResponse response) {
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		try {
			weather = parser.readFile(lat, lon, cnt, response);
		} catch (FileNotFound e1) {}
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

	public Vector<WeatherTemp> getTemperature(double lat, double lon, int cnt, HttpServletResponse response) {
		//aggiunta di un vettore WeatherCollection per prendere valori di media
		Vector<WeatherTemp> weatherTemp = new Vector<WeatherTemp>();
		JsonParser parser = new JsonParser();
		Vector<Weather> weather=new Vector<Weather>();
		try {
			weather = parser.readFile(lat, lon, cnt,response);
		} catch (FileNotFound e) {}
		for (int i=0; i<weather.size(); i++){
			weatherTemp.add(null);
			System.out.println(weather.get(i)==null);
			weatherTemp.get(i).setter(weather.get(i));
		}
		return weatherTemp;
	}
	
	
	/**
	 * Descrizione del metodo getPress.
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 * @throws FileNotFound 
	 */
	public Vector<WeatherPress> getPress(double lat, double lon, int cnt, HttpServletResponse response) {
		Vector<Weather> weather = new Vector<Weather>();
		Vector<WeatherPress> weatherPress = new Vector<WeatherPress>();
		JsonParser parser = new JsonParser();
		try {
			weather = parser.readFile(lat, lon, cnt, response);
		} catch (FileNotFound e) {}
		for(int i=0; i<weather.size(); i++){
			weatherPress.add(null);
			weatherPress.get(i).setter(weather.get(i));
		}
		return weatherPress;
	}


	public JSONObject Suggested(){
		return null;
	}

}