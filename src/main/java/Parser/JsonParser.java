package src.main.java.Parser;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import main.java.model.Weather;

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
	/*private JSONArray Jarray = null;*/
	private JSONObject Jobject = null;
	private Weather wet;
	
	/**
	 * The constructor.
	 */
	public JsonParser() {
		wet=new Weather();
	}

	/**
	 * Description of the method readFile.
	 * @param obj 
	 * @param url 
	 */
	public void readAPI(boolean obj, String url) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			Jobject=restTemplate.getForObject(url,JSONObject.class); //tramite restTemplate effettua la richiesta
																	 //ad OpenWeather e salva il risultato 
																	 //in un oggetto di tipo JSONObject
			
			wet.setNome((String)Jobject.get("name")); //salva il nome della citta 
			wet.setGiorno((long) Jobject.get("dt")); //salva il giorno
			wet.setPressione((double) Jobject.get("pressure")); //salva la pressione 
			wet.setTemp((double) Jobject.get("temp")); //salva la temperatura percepita
			wet.setTempMax((double) Jobject.get("temp_min")); //salva la temperatura massima	
			wet.setTempMin((double) Jobject.get("temp_max")); //salva la temperatura minima
			
		} catch (ResponseStatusException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Weather getWeather() {
		return wet;
	}

}
