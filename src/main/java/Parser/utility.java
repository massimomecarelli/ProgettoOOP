package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import model.Weather;

public class utility {
	
	public utility() {
		
	}
	
	/**
	 * Metodo che verifica se i dati ricevuti come parametro, sono già presenti nel file in basasandosi slla data odierna e sull'ora.
	 * Restituisce TRUE se non sono presenti, FALSE altrimenti
	 * @param weather dati da controllare
	 * @return check valore booleano che indica l'esito del controllo
	 */
	public boolean checkTime(Weather weather){
		File file=new File("src/main/resources/Weather.json");
		try {
			if(file.exists()) {
				Scanner file_input = new Scanner(new BufferedReader(new FileReader(file)));
				while(file_input.hasNextLine()) {
					JSONObject Jobject=new JSONObject(file_input.nextLine());
					if(Jobject.getDouble("lat")==weather.getLat()&&
						Jobject.getDouble("lon")==weather.getLon()){
						if(Jobject.getString("date").equals(weather.getGiorno())) {		
							return false;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * Metodo ausiliaro per popolare un oggetto di tipo Weather a partire da un JSONObject
	 * @param Jobject JSONObject da cui prendere i dati
	 * @return wet oggetto Weather popolato
	 */
	
	public Weather fillWet(JSONObject Jobject) {
		Weather wet=new Weather();
		try {
			wet.setNome((String)Jobject.get("nome"));
			wet.setLon((Double)Jobject.get("lon"));
			wet.setLat((Double)Jobject.get("lat"));
			wet.setDateTime((Integer)Jobject.get("dt"));
			wet.setGiorno((String)Jobject.getString("date"));
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

	/**
	 * Metodo ausiliaro per popolare uno JSONObject a partire da un oggetto di tipo Weather
	 * @param wet oggetto di tipo Weather da cui prendere i dati
	 * @return Jobject JSONObject popolato
	 */
	
	public JSONObject fillObject(Weather wet) {
		JSONObject Jobject=new JSONObject();
		try {
			Jobject.put("nome", wet.getNome());
			Jobject.put("lat", wet.getLat());
			Jobject.put("lon", wet.getLon());
			Jobject.put("dt", wet.getDateTime());
			Jobject.put("date", wet.getGiorno());
			Jobject.put("temp", wet.getTempPercepita());
			Jobject.put("tempMax", wet.getTempMax());
			Jobject.put("tempMin", wet.getTempMin());
			Jobject.put("pressure", wet.getPressione());
			Jobject.put("lettura", wet.getDataLettura());
		}catch(JSONException e) {
			e.printStackTrace();
		}
		return Jobject;
	}

	/**
	 * Metodo ausiliario per riempire un vettore di Weather, a partire dai dati ricevuti da OpenWeather.
	 * I dati ricevuti sono stati divisi in due oggetti per facilitarne la lettura.
	 * @param dt primo set di dati di OpenWeather
	 * @param city secondo set di dati di OpenWeather
	 * @return weather vettore di Weather popolato
	 */
	
	public Vector<Weather> setData(JSONArray dt, JSONObject city) {
		Vector<Weather> weather=new Vector<Weather>();
		LocalDateTime date=LocalDateTime.now();
		SimpleDateFormat newFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat oldFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	    	for(int i=0;i<dt.length();i++){
				Weather wet=new Weather();
				JSONObject list=dt.getJSONObject(i).getJSONObject("main");
				
				wet.setNome(city.getString("name")); //salva il nome della citta
				wet.setLat(city.getJSONObject("coord").getDouble("lat")); //salva latitudine della città
				wet.setLon(city.getJSONObject("coord").getDouble("lon")); //salva longitudine della città
				String day=dt.getJSONObject(i).getString("dt_txt"); 
				wet.setGiorno(newFormat.format(oldFormat.parse(day))); //salva giorno della previsione
				wet.setDateTime(dt.getJSONObject(i).getInt("dt"));//salva il datetime della previsione
				wet.setPressione(list.getInt("pressure")); //salva la pressione  
				wet.setTemp(list.getDouble("temp")); //salva la temperatura percepita
				wet.setTempMax(list.getDouble("temp_max")); //salva la temperatura massima	
				wet.setTempMin(list.getDouble("temp_min")); //salva la temperatura minima
				wet.setDataLettura(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")));
				
				weather.add(wet);
			}
	    }catch(JSONException | ParseException e) {
	    	e.printStackTrace();
	    }
		
		return weather;
	}

	/***
	 * Metodo ausiliario per riempire un oggetto di tipo Weather, a partire dai dati ricevuti da OpenWeather.
	 * I dati ricevuti sono stati divisi in due oggetti per facilitarne la lettura.
	 * @param dt primo set di dati di OpenWeather
	 * @param city secondo set di dati di OpenWeather
	 * @return wet oggetto di tipo Weather popolato
	 * @throws JSONException eccezione usata per gestire eventuali erroi nella lettura dei JSON
	 */
	
	public Weather getWet(JSONArray dt, JSONObject city) {
		
		Weather wet=new Weather();
		SimpleDateFormat newFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat oldFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			JSONObject list=dt.getJSONObject(0).getJSONObject("main");	
			wet.setNome(city.getString("name")); //salva il nome della citta
			wet.setLat(city.getJSONObject("coord").getDouble("lat")); //salva latitudine della città
			wet.setLon(city.getJSONObject("coord").getDouble("lon")); //salva longitudine della città
			String day=dt.getJSONObject(0).getString("dt_txt"); 
			wet.setGiorno(newFormat.format(oldFormat.parse(day))); //salva giorno della previsione
			wet.setDateTime(dt.getJSONObject(0).getInt("dt"));//salva il datetime della previsione
			wet.setPressione(list.getInt("pressure")); //salva la pressione  
			wet.setTemp(list.getDouble("temp")); //salva la temperatura percepita
			wet.setTempMax(list.getDouble("temp_max")); //salva la temperatura massima	
			wet.setTempMin(list.getDouble("temp_min")); //salva la temperatura minima
		}catch(JSONException | ParseException e) {
			e.printStackTrace();
		}
			
		return wet;
	}
	
	
	public Weather getActual(double lat, double lon) {
		
		Weather wet = null;
		String key;
		try {
			
			key=Files.readString(Path.of("src/main/resources/key.txt"));
			String API="http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+
					   "&appid="+key+"&cnt="+1+"&units=metric&lang=it";
			String temp=new RestTemplate().getForObject(API,String.class);
			JSONObject Jobject=new JSONObject(temp);
			JSONObject city=Jobject.getJSONObject("city");
			JSONArray dt=Jobject.getJSONArray("list");
			wet=getWet(dt,city);
			
		} catch (IOException |JSONException e) {
			e.printStackTrace();
		}
		System.out.println(wet.getGiorno());
		return wet;
		
	}
	
}
