package Parser;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.Vector;

import model.Weather;

/**
 * Description of JsonParser.
 * 
 * @author Massimo
 */
public class JsonParser {
	/**
	 * Description of the property weatherServices.
	 */
	//public Vector<WeatherService> weatherServices = new Vector<WeatherService>();
	private JSONArray Jarray;
	private JSONObject Jobject;
	private Vector<Weather> weather=new Vector<Weather>();
	private Weather wet;
	
	/**
	 * The constructor.
	 */
	public JsonParser() {
		wet=new Weather();
	}
	/**
	 * 
	 * @param dt
	 * @param list
	 * @param city
	 * @throws JSONException
	 */
	
	public void setData(JSONArray dt, JSONObject city) throws JSONException {
		for(int i=0;i<dt.length();i++){
			JSONObject list=dt.getJSONObject(i).getJSONObject("main");
			
			wet.setNome((String)city.get("name")); //salva il nome della citta
			wet.setGiorno((int)dt.getJSONObject(i).get("dt"));//salva il giorno
			wet.setPressione((int)list.get("pressure")); //salva la pressione  
			wet.setTemp((Double)list.get("temp")); //salva la temperatura percepita
			wet.setTempMax((Double)list.get("temp_max")); //salva la temperatura massima	
			wet.setTempMin((Double)list.get("temp_min")); //salva la temperatura minima
			
			weather.add(wet);
		}
	}

	/**
	 * Questo metodo si occupa di effettuare la richiesta all'API di OpenWeather, dopo aver ricevuto risposta
	 * il metodo popolera il Vector weather tramite il metodo setData
	 * @param obj 
	 * @param url 
	 */
	public void readAPI(String url) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			String temp=restTemplate.getForObject(url,String.class); //tramite restTemplate effettua la richiesta
																		 //ad OpenWeather e salva il risultato 
																		 //in un oggetto di tipo JSONObject
			Jobject=new JSONObject(temp);
			JSONObject city=Jobject.getJSONObject("city");
			JSONArray dt=Jobject.getJSONArray("list");
				
			setData(dt,city);
		} catch (ResponseStatusException | JSONException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Weather> getWeather() {
		return weather;
	}

}
