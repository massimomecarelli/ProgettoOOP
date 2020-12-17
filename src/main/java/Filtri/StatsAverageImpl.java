/**
 * 
 */
package Filtri;

/**
 * @author Massimo
 *
 */
public class StatsAverageImpl {
	package Filtri;

	import java.util.Vector;
	import org.json.*;

	import Parser.JsonParser;
	import model.WeatherCollection;
	import Service.*;
	import model.Weather;


	public class StatsAverageImpl implements StatsAverageInterface{
		
		//metodo che calcola la media dei valori di un vettore
		public double getMedia(Vector<Double> vett) {
			double t=0;
			for (int i=0; i<vett.size()-1; i++) {
				t=vett.elementAt(i)+vett.elementAt(i+1);
			}
			t/=vett.size();
			return t;
		}

	}

}
