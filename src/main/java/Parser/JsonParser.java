package Parser;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import Service.utility;

import org.json.JSONObject;
import org.apache.tomcat.jni.File;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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
	//private JSONArray Jarray;
	private JSONObject Jobject;
	private Vector<Weather> weather=new Vector<Weather>();
	private Weather wet;
	private utility util=new utility();
	
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
			
			weather=util.setData(dt,city);
		} catch (ResponseStatusException | JSONException e) {
			e.printStackTrace();
		}
		writeFile();
	}
	
	public void writeFile() {
		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/Weather.json",true)));
			
			for(int i=0;i<weather.size();i++) {
				Jobject=util.fillObject(weather.get(i));
				file_output.println(Jobject);
			}
			
			System.out.println("File salvato!");
			file_output.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errore salvataggio file!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Weather> readFile(double lat, double lon, int cnt) {
		try {
			Scanner file_input = new Scanner(new BufferedReader(new FileReader("src/main/resources/Weather.txt")));
			while(file_input.hasNextLine()){
				Jobject=new JSONObject(file_input.nextLine());
				
				if(Jobject.getDouble("lat")==lat&&Jobject.getDouble("lon")==lon){
					wet=util.fillWet(Jobject);
					weather.add(0,wet);
					if(weather.size()==cnt+1) {
						weather.remove(cnt-1);
					}
				}
			}
			file_input.close();
			
		} catch (NullPointerException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weather;
	}

	
	public Vector<Weather> getWeather() {
		return weather;
	}

}
