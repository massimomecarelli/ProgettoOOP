package filtri;

import java.util.Vector;

/**
 * @author Massimo Mecarelli
 * 
 * Interfaccia con un metodo per il calcolo della media dei valori di un vettore.
 */

public interface StatsAverageInterface {
	/*
	 * Metodo che calcola la media dei valori di un vettore.
	 * @param Vector<Double>
	 * @return double
	 */
			public double getMedia(Vector<Double> vett);
}
