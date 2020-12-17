/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package Filtri;

import java.util.Vector;
import org.json.*;

import Parser.JsonParser;
import model.WeatherCollection;
import Service.*;
import model.Weather;
import Filtri.StatsAverageImpl;



/**
 * Description of statsRequest.
 * 
 * @author Massimo
 */
public class StatsRequest {
	

	/**
	 * The constructor.
	 */
	public StatsRequest() {
		
	}

	/**
	 * Description of the method getAll.
	 * @param lat
	 * @param lon 
	 * @param cnt 
	 */
	public Vector<Weather> getAll(String key, double lat, double lon, int cnt) {
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt);
		return weather;
	}

	/**
	 * Description of the method getTemperature.
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 */
	public JSONObject getTemperature(double lat, double lon, int cnt) {
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		weather = parser.readFile(lat, lon, cnt);
		JSONObject Obj = new JSONObject();
		Vector<Double> tTot, tPerc;
		for (int i=1; i<=cnt; i++) {
			JSONObject obj = new JSONObject();
			obj.put("TempMax", weather.getTempMax());
			obj.put("TempMin",weather.getTempMin());
			obj.put("TempPercepita",weather.getTempPercepita());
			Obj.put("giorno "+i, obj);
			tTot.add(weather.getTempMax());
			tTot.add(weather.getTempMin());
			tPerc.add(weather.getTempPerc());
		}
		StatsAverageImpl average=new StatsAverageImpl();
		Obj.put("Media temperature", average.getMedia(tTot));
		Obj.put("Media temperatura percepita", average.getMedia(tPerc);
		return Obj;
	}
	
	/**
	 * Description of the method getPress.
	 * @param lat 
	 * @param lon 
	 * @param cnt
	 */
	public JSONObject getPress(double lat, double lon, int cnt) {
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		JSONObject obj = new JSONObject();
		weather = parser.readFile(lat, lon, cnt);
		obj.put("Pressione",weather.getPressione());	
		return obj;
	}

}