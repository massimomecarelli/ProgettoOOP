package Filtri;

import java.util.Vector;
import model.*;


public class StatsMaxMin {
	double max=0;
	double min=0;
	
	StatsMaxMin(){}
	
	//metodo che calcola il valore minimo di un vettore di pressioni
	public void setMin(Vector<WeatherPress> vett) {
		for (int i=0; i<vett.size()-1; i++) {
			if (vett.elementAt(i).getPressione()<min)
				min=vett.elementAt(i).getPressione();
		}
	}

	//metodo che calcola il valore massimo di un vettore di pressioni
	public void setMax(Vector<WeatherPress> vett) {
		for (int i=0; i<vett.size()-1; i++) {
			if (vett.elementAt(i).getPressione() > max)
				max=vett.elementAt(i).getPressione();
		}
	}
	
	//metodo che calcola il valore minimo di un vettore di temperature,
	//per far ciò confronto solo i valori minimi registrati,
	//la firma è diversificata da un char
	public void setMin(Vector<WeatherTemp> vett, char t) {
		for (int i=0; i<vett.size()-1; i++) {
			if (vett.elementAt(i).getTMin()<min)
				min=vett.elementAt(i).getTMin();
		}
	}

	//metodo che calcola il valore massimo di un vettore di temperature,
	//per far ciò confronto solo i valori massimi registrati
	public void setMax(Vector<WeatherTemp> vett, char t) {
		for (int i=0; i<vett.size()-1; i++) {
			if (vett.elementAt(i).getTMax() > max)
				max=vett.elementAt(i).getTMax();
		}
	}
	
	
	
	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
	
}