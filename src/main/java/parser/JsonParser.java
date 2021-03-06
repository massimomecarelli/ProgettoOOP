package parser;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import model.Weather;
import errors.FileNotFound;

/**
 * Classe che gestisce le chiamate ad OpenWeather
 * e la lettura/scrittura su/da file
 * 
 * @author Massimo Mecarelli
 * @author Marco Pasquale Martino
 */
public class JsonParser {
	
	/**
	 * Attributo per memorizzare JSONObject ottenuti da OpenWeather.
	 */
	private JSONObject Jobject;
	/**
	 * Attributo utilizzato per memorizzare i dati ottenuti convertendo il JSONObject.
	 */
	private Vector<Weather> weather=new Vector<Weather>();
	/**
	 * Attributo utlizzato per memorizzare temporanemente un oggetto Weather.
	 */
	private Weather wet;
	/**
	 * Istanza di una classe per utilizzare dei metodi ausiliari.
	 */
	private Utility util=new Utility();
	
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
	 * @param url : Stringa contente url di OpenWeather per la richiesta dei dati.
	 * @return weather : oggetto contente i dati ricevuti da OpenWeather.
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
		return weather;
	}
	
	/**
	 * Questo metodo si occupera di inserire i dati letti tramite il metodo readAPI all'interno di un file .json locale.
	 * Se il file json è presente, i nuovi dati verranno accodati ai precedenti; se il file json non dovesse essere presente,
	 * allora verrà creato e i dati saranno inseriti al suo interno.
	 */
	public void writeFile() {
		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/Weather.json",true)));
			for(int i=0;i<weather.size();i++) {
				if(util.checkTime(weather.get(i))) {
					Jobject=util.fillObject(weather.get(i));
					file_output.println(Jobject);
					System.out.println("File salvato!");
				}
			}
			file_output.close();
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
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @return weather : Vector di Weather contenente i dati letti dal file. 
	 * 		   Il metodo potrebbe restituire null nel caso in cui ci siano dei problemi nella lettura del file.
	 * @throws FileNotFound eccezione per gestire eventuali errori per la mancanza di file 
	 */

	public Vector<Weather> readFile(double lat, double lon, int cnt,HttpServletResponse response) throws FileNotFound{
		File file=new File("src/main/resources/Weather.json");
		String key;
		try {
			key = Files.readString(Path.of("src/main/resources/key.txt"));
			Vector<Weather> temp=readAPI("http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+
					"&appid="+key+"&cnt="+1+"&units=metric&lang=it");
			Scanner file_input = new Scanner(new BufferedReader(new FileReader(file)));
			while(file_input.hasNextLine()){
				Jobject=new JSONObject(file_input.nextLine());
				if(Jobject.getDouble("lat")==lat&&Jobject.getDouble("lon")==lon&&
				   Jobject.getString("date").equals(temp.get(0).getGiorno())){
					wet=util.fillWet(Jobject);
					weather.add(0,wet);
					if(weather.size()==cnt+1) {
						weather.remove(cnt-1);
					}
				}
			}
			file_input.close();
			return weather;
		} catch (NullPointerException | JSONException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			throw new FileNotFound(e,response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Metodo utilizzato per leggere i dati da un file, è simile a readFile ma effettua un controllo in meno poiché i dati letti
	 * non verranno mostrati direttamente all'utente, ma elaborati dalla classe statsRequest.
	 * @param lat : latitudine della città ricercata;
	 * @param lon : longitudine della città ricercata;
	 * @param cnt : quantità di dati richiesta;
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @return weather : Vector di Weather contenente i dati letti dal file. 
	 * 		   Il metodo potrebbe restituire null nel caso in cui ci siano dei problemi nella lettura del file.
	 * @throws FileNotFound eccezione per gestire eventuali errori per la mancanza di file 
	 */
	public Vector<Weather> readFileStats(double lat, double lon, int cnt,HttpServletResponse response) throws FileNotFound{
		File file=new File("src/main/resources/Weather.json");
		try {
			Scanner file_input = new Scanner(new BufferedReader(new FileReader(file)));
			while(file_input.hasNextLine()){
				Jobject=new JSONObject(file_input.nextLine());
				if(Jobject.getDouble("lat")==lat&&Jobject.getDouble("lon")==lon){
					wet=util.fillWet(Jobject);
					weather.add(wet);
				}
				if(weather.size()==cnt) break;
			}
			file_input.close();
			return weather;
		} catch (NullPointerException | JSONException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			throw new FileNotFound(e,response);
		}
		return null;
	}
}
