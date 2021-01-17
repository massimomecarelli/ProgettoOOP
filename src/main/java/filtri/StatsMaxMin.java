package filtri;

import java.util.Vector;

import model.WeatherPress;
import model.WeatherTemp;

/*
 * Classe che permette di calcolare massimo e minimo dato un vettore di pressioni o di temperature
 */
public class StatsMaxMin {
	double max=0;
	double min=0;
	
	public StatsMaxMin(){}
	
	//metodo che calcola il valore minimo di un vettore di pressioni
	public void setMin(Vector<WeatherPress> vett) {
		this.min = vett.elementAt(0).getPressione();
		for (int i=1; i<vett.size(); i++) {
			if (vett.elementAt(i).getPressione() < min)
				this.min=vett.elementAt(i).getPressione();
		}
	}

	/*
	* Metodo che calcola il valore massimo di un vettore di pressioni
	*/
	public void setMax(Vector<WeatherPress> vett) {
		this.max = vett.elementAt(0).getPressione();
		for (int i=1; i<vett.size(); i++) {
			if (vett.elementAt(i).getPressione() > max)
				this.max=vett.elementAt(i).getPressione();
		}
	}
	
	/*Metodo che calcola il valore minimo di un vettore di temperature,
	* per far ciò confronto solo i valori minimi registrati,
	* la firma è diversificata da un char
	*/
	public void setMin(Vector<WeatherTemp> vett, char t) {
		this.min = vett.elementAt(0).getTMin();
		for (int i=1; i<vett.size(); i++) {
			if (vett.elementAt(i).getTMin() < min)
				this.min=vett.elementAt(i).getTMin();
		}
	}

	/*
	 *Metodo che calcola il valore massimo di un vettore di temperature,
	 * per far ciò confronto solo i valori massimi registrati
	*/
	public void setMax(Vector<WeatherTemp> vett, char t) {
		this.max = vett.elementAt(0).getTMax();
		for (int i=1; i<vett.size(); i++) {
			if (vett.elementAt(i).getTMax() > max)
				this.max=vett.elementAt(i).getTMax();
		}
	}
	
	
	
	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
	
}