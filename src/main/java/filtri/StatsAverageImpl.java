package filtri;

import java.util.Vector;

/**
 * Classe che implementa un metodo per il calcolo della media dei valori di un vettore.
 *
 * @author Massimo Mecarelli
 */	
public class StatsAverageImpl implements StatsAverageInterface{
		
	/**
	 * Metodo che calcola la media dei valori di un vettore.
	 * @param vett : il vettore con i dati da sottoporre ad operazione di media aritmetica
	 * @return t : la media
	 */
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
