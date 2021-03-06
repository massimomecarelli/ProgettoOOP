package filtri;

import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import errors.*;
import model.Weather;
import model.WeatherCollection;
import model.WeatherPress;
import model.WeatherTemp;
import parser.JsonParser;

/**
 * Classe che restituisce dati utili direttamente a WeatherService.
 * @author Massimo Mecarelli
 * @author Marco Pasquale Martino
 */
public class StatsRequest {
	
	/**
	 * Attributo che contiene il valore massimo di una sequenza.
	 */
	private double Max;
	/**
	 * Attributo che contiene il valore minimo di una sequenza.
	 */
	private double Min;
	/**
	 * Attributo che contien il nome della città a cui si riferiscono max e min.
	 */
	private String nome;

	/**
	 * Costruttore nullo.
	 */
	public StatsRequest() {
		
	}

	/**
	 * Metodo che calcola la media di temperatura degli ultimi giorni (inserito dall'utente)
	 *  ritornando un oggetto che possiede come parametri la temperatura media e la media percepita
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt lasso di tempo su cui generare le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @return WeatherCollection : oggetto contente i dati relativi alla temperatura media e percepita.
	 * @throws FileIsEmpty : eccezione che gestisce eventuali errori dovuti ad un file vuoto.
	 * @throws FileNotFound : eccezione che gestisce eventuali errori dovuti ad un file non trovato.
	 */
	public WeatherCollection getTemperatureAvrg(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		weather = parser.readFileStats(lat, lon, cnt, response);
		
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!", response);
		
		StatsAverageImpl average=new StatsAverageImpl();
		WeatherCollection collection = new WeatherCollection();
		Vector<Double> tTot = new Vector<Double>();
		Vector<Double> tPerc = new Vector<Double>();
		
		collection.setNome(weather.get(0).getNome());
		try {
			for (int i=0; i<weather.size(); i++) {
				tTot.add(weather.get(i).getTempMax());
				tTot.add(weather.get(i).getTempMin());
				tPerc.add(weather.get(i).getTempPercepita());
			}
			collection.setTempMedia(average.getMedia(tTot));
			collection.setTempMediaPerc(average.getMedia(tPerc));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return collection;
	}

	/**
	 * Metodo che prende il valore massimo delle temperature in un determinato lasso di tempo (deciso dall'utente), di una città scelta dall'utente
	 * tramite l'inserimento delle cue coordinate.
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt lasso di tempo su cui generare le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @throws FileIsEmpty : eccezione che gestisce eventuali errori dovuti ad un file vuoto.
	 * @throws FileNotFound : eccezione che gestisce eventuali errori dovuti ad un file non trovato.
	 */
	public void setTemperatureMax(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		Vector<WeatherTemp> weatherTemp = new Vector<WeatherTemp>();
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		WeatherTemp wTemp;
		
		weather = parser.readFileStats(lat, lon, cnt, response);
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!",response);
		nome=weather.get(0).getNome();
		System.out.println(weather.size());
		for (int i=0; i<weather.size(); i++){
			wTemp=new WeatherTemp(weather.get(i));
			weatherTemp.add(wTemp);
		}
		//trovo il valore massimo
		StatsMaxMin max = new StatsMaxMin();
		max.setMax(weatherTemp, 't');
		this.Max=max.getMax();
	}

	/**
	 * Metodo che prende il valore minimo delle temperature nei giorni precedenti (inserito dall'utente)
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt lasso di tempo su cui generare le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @throws FileIsEmpty : eccezione che gestisce eventuali errori dovuti ad un file vuoto.
	 * @throws FileNotFound : eccezione che gestisce eventuali errori dovuti ad un file non trovato.
	 */
	public void setTemperatureMin(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		Vector<WeatherTemp> weatherTemp = new Vector<WeatherTemp>();
		Vector<Weather> weather = new Vector<Weather>();
		JsonParser parser = new JsonParser();
		WeatherTemp wTemp;
		
		weather = parser.readFileStats(lat, lon, cnt, response);
		
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!",response);
		nome=weather.get(0).getNome();
		for (int i=0; i<weather.size(); i++){
			wTemp=new WeatherTemp(weather.get(i));
			weatherTemp.add(wTemp);
		}
		//trovo il valore minimo
		StatsMaxMin min = new StatsMaxMin();
		min.setMin(weatherTemp, 't');
		this.Min = min.getMin();
		
	}
	
	/**
	 * Metodo che prende il valore massimo delle pressioni nei giorni precedenti (inserito dall'utente)
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt lasso di tempo su cui generare le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @throws FileIsEmpty : eccezione che gestisce eventuali errori dovuti ad un file vuoto.
	 * @throws FileNotFound : eccezione che gestisce eventuali errori dovuti ad un file non trovato.
	 */
	public void setPressMassima(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		Vector<Weather> weather = new Vector<Weather>();
		Vector<WeatherPress> weatherPress = new Vector<WeatherPress>();
		WeatherPress wPress;
		JsonParser parser = new JsonParser();
		
		weather = parser.readFileStats(lat, lon, cnt, response);
		
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!",response);
		nome=weather.get(0).getNome();
		for(int i=0; i<weather.size(); i++){
			wPress=new WeatherPress(weather.get(i));
			weatherPress.add(wPress);
		}
		//trovo il valore massimo
		StatsMaxMin max = new StatsMaxMin();
		max.setMax(weatherPress);
		this.Max=max.getMax();
	}

	/**
	 * Metodo che prende il valore minimo delle pressioni nei giorni precedenti (inserito dall'utente)
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt lasso di tempo su cui generare le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @throws FileIsEmpty : eccezione che gestisce eventuali errori dovuti ad un file vuoto.
	 * @throws FileNotFound : eccezione che gestisce eventuali errori dovuti ad un file non trovato.
	 */
	public void setPressMinima(double lat, double lon, int cnt, HttpServletResponse response) throws FileIsEmpty, FileNotFound{
		Vector<Weather> weather = new Vector<Weather>();
		Vector<WeatherPress> weatherPress = new Vector<WeatherPress>();
		JsonParser parser = new JsonParser();
		WeatherPress wPress;
		
		weather = parser.readFileStats(lat, lon, cnt, response);
		
		if (weather==null)
			throw new FileIsEmpty ("Il file è vuoto!",response);
		nome=weather.get(0).getNome();
		for(int i=0; i<weather.size(); i++){
			wPress=new WeatherPress(weather.get(i));
			weatherPress.add(wPress);
		}
		//trovo il valore minimo
		StatsMaxMin min = new StatsMaxMin();
		min.setMin(weatherPress);
		this.Min=min.getMin();
	}

	/**
	 * Metodo che restituisce il valore minimo delle pressioni (o delle temperature) nei giorni precedenti (inserito dall'utente)
	 * @return Min double contente il minimo
	 */
	public double getMin(){
		return Min;
	}

	/**
	 * Metodo che restituisce il valore massimo delle pressioni (o delle temperature) nei giorni precedenti (inserito dall'utente)
	 * @return Max double contente il valore massimo
	 */
	public double getMax(){
		return Max;
	}
	
	/**
	 * Metodo che restituisce il nome della città
	 * @return nome stringa contente il nome
	 */
	public String getNome() {
		return nome;
	}

}