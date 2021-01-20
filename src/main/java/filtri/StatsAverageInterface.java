package filtri;

import java.util.Vector;

/**
 * @author Massimo Mecarelli
 * 
 * Interfaccia con un metodo per il calcolo della media dei valori di un vettore.
 */

public interface StatsAverageInterface {
	/**
	 * Metodo che calcola la media dei valori di un vettore.
	 * @param vett : vettore contente i dati di cui calcolare la media.
	 * @return media dei valori contenuti nel vettore di Double.
	 */
	public double getMedia(Vector<Double> vett);
}
