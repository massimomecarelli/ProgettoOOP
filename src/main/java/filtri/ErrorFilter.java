package filtri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import errors.FileNotFound;
import model.Weather;
import model.ModelError;
import parser.Utility;

public class ErrorFilter {
	private Vector<Weather> weather;
	
	public ErrorFilter() {
		weather=new Vector<Weather>();
	}
	
	public void setWeather(Vector<Weather> weather) {
		this.weather=weather;
	}
	
	public Vector<Weather> getWeather(){
		return weather;
	}
	
	/**
	 * Metodo che legge i dati da file weather.json, dopo di che calcola l'errore per ogni dato in base ai dati attuali e popola il Vector<Weather>
	 * in base alla percentuale massima tollerata, se l'errore calcolato è minore o uguale a quello massimo, i dati verranno inseriti, altrimenti saranno scartati.
	 * @param lat latitudine della città
	 * @param lon longitudine della città
	 * @param cnt quantità di dati su cui generale le statistiche
	 * @param response paramento ottenuto dal server, utilizzato per gestire eventuali errori
	 * @param err percentuale massima di errore da mostrare
	 * @return error dati sulle statische riguardante gli errori
	 * @throws FileNotFound eccezione che gestisce eventuali errori dovuti alla mancanza del file
	 */
	public Vector<ModelError> getTempError(double lat, double lon, int cnt, double err, HttpServletResponse response) throws FileNotFound {
		int check=0;
		Utility util=new Utility();
		Vector<ModelError> error=new Vector<ModelError>();
		Weather actual=util.getActual(lat,lon);
		try {
			File file=new File("src/main/resources/weather.json");
			Scanner file_in=new Scanner(new BufferedReader(new FileReader(file)));
			while(file_in.hasNextLine()) {
				JSONObject Jobject=new JSONObject(file_in.nextLine());
				if(Jobject.getDouble("lat")==lat&&Jobject.getDouble("lon")==lon
				   &&Jobject.getString("date").equals(actual.getGiorno())) {
					if(check<cnt) {
						weather.add(util.fillWet(Jobject));
						check++;
					}else {
						weather.add(0, util.fillWet(Jobject));
						weather.remove(weather.size()-1);
					}
				}
			}
			for(int i=0;i<weather.size();i++) {
				double er=calculateError(weather.get(i), actual);
				if(er<=err) {
					String tmp=Double.toString(er)+"%";
					ModelError temp=new ModelError(weather.get(i), actual, tmp);
					error.add(temp);
				}
			}
		}catch(JSONException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			throw new FileNotFound(e,response);
		}
		return error;
	}
	
	/**
	 * Metodo che calcola la percentuale di errore complessiva, relativa a temperatura massima, minima, percepita e alla pressione.
	 * @param wet dati storici su cui calcolare l'errore
	 * @param actual dati meteorologi attuali su cui basarci per l'errore
	 * @return error percentuale di errore trovata
	 */
	public double calculateError(Weather wet, Weather actual) {
		double error,max,min,perc,press;
		max=Math.abs(wet.getTempMax()-actual.getTempMax());
		max=(double)(Math.round(((max/actual.getTempMax())*100)*Math.pow(10,2))/Math.pow(10,2));
		
		min=Math.abs(wet.getTempMin()-actual.getTempMin());
		min=(double)(Math.round(((min/actual.getTempMin())*100)*Math.pow(10,2))/Math.pow(10,2));
		
		perc=Math.abs(wet.getTempPercepita()-actual.getTempPercepita());
		perc=(double)(Math.round(((perc/actual.getTempPercepita())*100)*Math.pow(10,2))/Math.pow(10,2));
		
		press=Math.abs(wet.getPressione()-actual.getPressione());
		press=(double)(Math.round(((press/actual.getPressione())*100)*Math.pow(10,2))/Math.pow(10,2));
		
		error=(double)(Math.round(((max+min+perc+press)/4)*Math.pow(10,2))/Math.pow(10,2));
		return error;
	}
}


