package model;

/**
 * Description of Weather.
 * 
 * @author Massimo
 */
public class Weather {
	/**
	 * Valorori che indicano le coordinate cartesiane di una città.
	 */
	private double lat=0;
	private double lon=0;
	/**
	 * Valore che indica la pressione in un giorno.
	 */
	private int Pressione = 0;

	/**
	 * Valore che indica la temperatura percepita in un giorno.
	 */
	private double TempPercepita = 0;

	/**
	 * Valore che indica il giorno.
	 */
	private int dateTime = 0;

	/**
	 * Valore che indica il giorno in dd/MM/yyyy.
	 */
	private String giorno = "";	
	/**
	 * Valore che indica il nome della città.
	 */
	private String Nome = "";

	/**
	 * Valore che indica la temperatura massima raggiungibile in un giorno.
	 */
	private double TempMax = 0;

	/**
	 * Valore che indica la temperatura minima raggiungibile in un giorno.
	 */
	private double TempMin = 0;
	
	/**
	 * Valore che indica la data in cui è stata fatta la lettura
	 */
	private String dataLettura;
	
	public Weather() { }
	
	public Weather(double lat,double lon, double min, double max, double perc, int press, int dt, String dataLett, String giorno, String nome) {
		setLat(lat);
		setLon(lon);
		setTempMin(min);
		setTempMax(max);
		setTemp(perc);
		setPressione(press);
		setDateTime(dt);
		setDataLettura(dataLett);
		setGiorno(giorno);
		setNome(nome);
	}
	
	/**
	 * Metodo che restitusice la latitudine della città.
	 * @return lat : latitudine della città.
	 */
	public double getLat() {
		return lat;
	}
	
	/**
	 * Metodo che restitusice la longitudine della città.
	 * @return lon : longitudine della città.
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * Metodo che restitusice la temperatura minima.
	 * @return tempMin : temperatura minima.
	 */
	public double getTempMin() {
		return TempMin;
	}

	/**
	 * Metodo che restituisce il nome della città.
	 * @return Nome : nome della città.
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * Metodo che restituisce la pressione.
	 * @return Pressione : pressione.
	 */
	public int getPressione() {
		return Pressione;
	}

	/**
	 * Metodo che restituisce la temperatura massima.
	 * @return TempMax : temperatura massima.
	 */
	public double getTempMax() {
		return TempMax;
	}

	/**
	 * Metodo che restituisce la temperatura percepuita.
	 * @return TempPercerpita : temperatura percepita.
	 */
	public double getTempPercepita() {
		return TempPercepita;
	}
	
	/**
	 * Metodo che restituisce il giorno in cui è stata fatta la lettura.
	 * @return dataLettura : giorno in cui è stata effettuata la lettura.
	 */
	public String getDataLettura() {
		return dataLettura;
	}
	
	/**
	 * Metodo che restituisce il giorno a cui si riferiscono i dati, in formato dd-MM-yyyy.
	 * @return giorno : giorno a cui si riferiscono i dati.
	 */
	public String getGiorno() {
		return giorno;
	}
	
	/**
	 * Metodo che restituisce il giorno in formato dateTime.
	 * @return dateTime : giorno in formato dateTime.
	 */
	public int getDateTime() {
		return dateTime;
	}

	/**
	 * Metodo per impostare la latitudine della città. 
	 * @param lat : latitudine della città.
	 */
	public void setLat(double lat) {
		this.lat=lat;
	}
	
	/**
	 * Metodo per impostare la longitudine della città.
	 * @param lon : longitudine della città.
	 */
	public void setLon(double lon) {
		this.lon=lon;
	}
	
	/**
	 * Metodo pe rimpostare il nome della città.
	 * @param nome : nome della città.
	 */
	public void setNome(String nome) {
		this.Nome=nome;
	}
	
	/**
	 * Metodo per impostare il giorno a cui si riferiscono i dati in formato dd-MM-yyyy.
	 * @param giorno : giorno in formato dd-MM-yyyy.
	 */
	public void setGiorno(String giorno) {
		this.giorno=giorno;
	}
	
	/**
	 * Metodo per impostare il giorno a cui si riferscono i dati in formato datetime.
	 * @param dateTime : giorno in formato datetime.
	 */
	public void setDateTime(int dateTime) {
		this.dateTime=dateTime;
	}
	
	/**
	 * Metodo per impostare la temeperatura minima.
	 * @param tempMin : temperatura minima.
	 */
	public void setTempMin(double tempMin) {
		this.TempMin=tempMin;
	}

	/**
	 * Metodo per impostare la temperatra massima.
	 * @param tempMax : temperatura massima.
	 */
	public void setTempMax(double tempMax) {
		this.TempMax=tempMax;
	}
	
	/**
	 * Metodo per impostare la pressione.
	 * @param pressione
	 */
	public void setPressione(int pressione) {
		this.Pressione=pressione;
	}
	
	/**
	 * Metodo per impostare la temperatura percepita.
	 * @param temp : temperatura percepita.
	 */
	public void setTemp(double temp) {
		this.TempPercepita=temp;
	}
	
	/**
	 * Metodo per impostare il giorno in cui è stata effettuata la lettura.
	 * @param dataLettura : giorno in cui è stata effettuata la lettura.
	 */
	public void setDataLettura(String dataLettura) {
		this.dataLettura=dataLettura;
	}
}	
