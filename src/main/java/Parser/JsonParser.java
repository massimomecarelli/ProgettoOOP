package Parser;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.json.JSONObject;
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
 * Classe che gestisce le chiamate ad OpenWeather
 * e la lettura/scrittura su/da file
 * 
 * @author Massimo Mecarelli
 * @author Marco Pasquale Martino
 */
public class JsonParser {
	
	private JSONObject Jobject;
	private Vector<Weather> weather=new Vector<Weather>();
	private Weather wet;
	private utility util=new utility();
	
	/**
	 * Costruttore della classe parser che istanzia un oggetto della classe Weather.
	 */
	public JsonParser() {
		wet=new Weather();
	}
	
	/**
	 * Questo metodo si occupa di effettuare la richiesta all'API di OpenWeather, dopo aver ricevuto risposta
	 * il metodo popolera il Vector weather, da fortnire in output all'utente, tramite il metodo setData.
	 * Infine richiamera il metodo writeFile per la scrittura dei dati sul file .json locale.
	 * 
	 * @param url : Stringa contente url di OpenWeather per la richiesta dei dati.
	 */
	public Vector<Weather> readAPI(String url) {
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
		System.out.println(weather);
		return weather;
	}
	
	/**
	 * Questo metodo si occupera di inserire i dati letti tramite il metodo readAPI all'interno di un file .json locale.
	 * Se il file json è presente, i nuovi dati verranno accodati ai precedenti; se il file json non dovesse essere presente,
	 * allora verrà creato e i dati saranno inseriti al suo interno.
	 */
	public void writeFile() {
		try {
			if(util.checkTime(weather)) {
				PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/Weather.json",true)));
				
				for(int i=0;i<weather.size();i++) {
					Jobject=util.fillObject(weather.get(i));
					file_output.println(Jobject);
				}
				
				System.out.println("File salvato!");
				file_output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errore salvataggio file!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che si occupa della lettura dei dati dal file json locale. Saranno forniti latitudine e longitudine come parametri,
	 * per poter eseguire la ricerca della città nel file; una volta trovata, il file verrà letto finché non sarà finito.
	 * Il parametro cnt servirà per poter prendere il numero di dati in base al filtro applicato:
	 *  -giornaliero(cnt=1): il metodo prenderà l'ultimo dato letto;
	 *  -settimanale(cnt=7): il metodo prenderà i dati riguardanti l'ultima settimana; 
	 * 	-mensile(cnt=30): il metodo prenderà i dati riguardanti l'ultimo mese.
	 * 
	 * @param lat : latitudine della città ricercata;
	 * @param lon : longitudine della città ricercata;
	 * @param cnt : quantità di dati richiesta;
	 * @return Vector di Weather contenente i dati letti dal file.
	 */
	
	public Vector<Weather> readFile(double lat, double lon, int cnt) {
		try {
			Scanner file_input = new Scanner(new BufferedReader(new FileReader("src/main/resources/Weather.json")));
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

}
