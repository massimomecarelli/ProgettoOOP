/**
 * 
 */
package filtri;

/**
 * @author Massimo Mecarelli
 * 
 * Classe che implementa un metodo per il calcolo della media dei valori di un vettore
 */

	import java.util.Vector;


	public class StatsAverageImpl implements StatsAverageInterface{
		
		//metodo che calcola la media dei valori di un vettore
		public double getMedia(Vector<Double> vett) {
			double t=vett.elementAt(0);
			for (int i=1; i<vett.size(); i++) {
				t+=vett.elementAt(i);
			}
			t/=vett.size();
			t=Math.round(t*Math.pow(10,2))/Math.pow(10,2);
			return t;
		}

	}
