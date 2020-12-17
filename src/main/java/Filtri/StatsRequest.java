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
	public Vector<Weather> getAll(double lat, double lon, int cnt) {
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
			obj.put("TempMax", weather.get(i-1).getTempMax());
			obj.put("TempMin",weather.get(i-1).getTempMin());
			obj.put("TempPercepita",weather.get(i-1).getTempPercepita());
			Obj.put("giorno "+i, obj);
			tTot.add(weather.get(i-1).getTempMax());
			tTot.add(weather.get(i-1).getTempMin());
			tPerc.add(weather.get(i-1).getTempPercepita());
		}
		StatsAverageImpl average=new StatsAverageImpl();
		Obj.put("Media temperature", average.getMedia(tTot));
		Obj.put("Media temperatura percepita", average.getMedia(tPerc));
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
		for(int i=0; i<weather.size(); i++)
			obj.put("Pressione", weather.get(i).getPressione());	
		return obj;
	}

}