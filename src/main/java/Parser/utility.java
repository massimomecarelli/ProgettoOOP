package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Weather;

public class utility {
	
	public utility() {
		
	}
	public boolean checkTime(Vector<Weather> weather){
		boolean check=true;
		File file=new File("src/main/resources/Weather.json");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");
		int day= LocalDateTime.now().getDayOfMonth();
		try {
			if(file.exists()) {
				Scanner file_input = new Scanner(new BufferedReader(new FileReader(file)));
				for(int i=0;i<weather.size();i++) {
					while(file_input.hasNextLine()) {
						JSONObject Jobject=new JSONObject(file_input.nextLine());
						if(Jobject.getDouble("lat")==weather.get(i).getLat()&&
						   Jobject.getDouble("lon")==weather.get(i).getLon()){
							LocalDateTime dateTime = LocalDateTime.parse(weather.get(i).getDataLettura(), formatter);
							if((day-dateTime.getDayOfMonth()==0)) {
								check=false;
							}
						}
					}
				}
			}return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return check;
	}
	public Weather fillWet(JSONObject Jobject) {
		Weather wet=new Weather();
		try {
			wet.setNome((String)Jobject.get("nome"));
			wet.setLon((Double)Jobject.get("lon"));
			wet.setGiorno((Integer)Jobject.get("dt"));
			wet.setTemp((Double)Jobject.get("temp"));
			wet.setTempMin((Double)Jobject.get("tempMin"));
			wet.setTempMax((Double)Jobject.get("tempMax"));
			wet.setPressione((Integer)Jobject.get("pressure"));
			wet.setDataLettura((String)Jobject.get("date"));
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
		LocalDateTime date=LocalDateTime.now();
	    
		for(int i=0;i<dt.length();i++){
			Weather wet=new Weather();
			JSONObject list=dt.getJSONObject(i).getJSONObject("main");
			
			wet.setNome(city.getString("name")); //salva il nome della citta
			wet.setLat(city.getJSONObject("coord").getDouble("lat")); //salva latitudine della città
			wet.setLon(city.getJSONObject("coord").getDouble("lon")); //salva longitudine della città
			wet.setGiorno(dt.getJSONObject(i).getInt("dt"));//salva il giorno
			wet.setPressione(list.getInt("pressure")); //salva la pressione  
			wet.setTemp(list.getDouble("temp")); //salva la temperatura percepita
			wet.setTempMax(list.getDouble("temp_max")); //salva la temperatura massima	
			wet.setTempMin(list.getDouble("temp_min")); //salva la temperatura minima
			wet.setDataLettura(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss")));
			
			weather.add(wet);
		}
		return weather;
	}
	
}
