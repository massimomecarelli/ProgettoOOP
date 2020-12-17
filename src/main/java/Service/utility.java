package Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Weather;

public class utility {
	
	public utility() {
		
	}
	
	public Weather fillWet(JSONObject Jobject) {
		Weather wet=new Weather();
		
		try {
			wet.setNome(Jobject.getString("name"));
			wet.setLon(Jobject.getDouble("lon"));
			wet.setGiorno(Jobject.getInt("dt"));
			wet.setTemp(Jobject.getDouble("temp"));
			wet.setTempMin(Jobject.getDouble("tempMin"));
			wet.setTempMax(Jobject.getDouble("tempMax"));
			wet.setPressione(Jobject.getInt("pressure"));
			wet.setDataLettura(Jobject.getString("date"));
		}catch(JSONException e) {
			e.printStackTrace();
		}
		return wet;
	}

	public JSONObject fillObject(Weather wet) {
		JSONObject Jobject=new JSONObject();
		try {
			Jobject.put("nome", wet.getNome());
			Jobject.put("lat", wet.getLat());
			Jobject.put("lon", wet.getLon());
			Jobject.put("dt", wet.getGiorno());
			Jobject.put("temp", wet.getTempPercepita());
			Jobject.put("tempMax", wet.getTempMax());
			Jobject.put("tempMin", wet.getTempMin());
			Jobject.put("pressure", wet.getPressione());
			Jobject.put("date", wet.getDataLettura());
		}catch(JSONException e) {
			e.printStackTrace();
		}
		return Jobject;
	}

	public Vector<Weather> setData(JSONArray dt, JSONObject city) throws JSONException {
		Vector<Weather> weather=new Vector<Weather>();
		LocalDate date=LocalDate.now();
	    
		for(int i=0;i<dt.length();i++){
			Weather wet=new Weather();
			JSONObject list=dt.getJSONObject(i).getJSONObject("main");
			
			wet.setNome(city.getString("name")); //salva il nome della citta
			System.out.println(wet.getNome());
			wet.setLat(city.getJSONObject("coord").getDouble("lat")); //salva latitudine della città
			wet.setLon(city.getJSONObject("coord").getDouble("lon")); //salva longitudine della città
			wet.setGiorno(dt.getJSONObject(i).getInt("dt"));//salva il giorno
			wet.setPressione(list.getInt("pressure")); //salva la pressione  
			wet.setTemp(list.getDouble("temp")); //salva la temperatura percepita
			wet.setTempMax(list.getDouble("temp_max")); //salva la temperatura massima	
			wet.setTempMin(list.getDouble("temp_min")); //salva la temperatura minima
			wet.setDataLettura(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			weather.add(wet);
		}
		return weather;
	}
	
}
