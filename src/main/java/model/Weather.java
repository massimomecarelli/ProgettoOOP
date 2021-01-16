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
	
	public Weather() {
		
	}
	
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
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	/**
	 * Description of the method getTempMin.
	 * @return 
	 */
	public double getTempMin() {
		return TempMin;
	}

	/**
	 * Description of the method getNome.
	 * @return 
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * Description of the method getPressione.
	 * @return 
	 */
	public int getPressione() {
		return Pressione;
	}

	/**
	 * Description of the method getTempMax.
	 * @return 
	 */
	public double getTempMax() {
		return TempMax;
	}

	/**
	 * Description of the method getTempPercepita.
	 * @return 
	 */
	public double getTempPercepita() {
		return TempPercepita;
	}
	
	public String getDataLettura() {
		return dataLettura;
	}
	
	public String getGiorno() {
		return giorno;
	}
	
	public int getDateTime() {
		return dateTime;
	}
	
	public void setLat(double lat) {
		this.lat=lat;
	}
	
	public void setLon(double lon) {
		this.lon=lon;
	}
	
	public void setNome(String nome) {
		this.Nome=nome;
	}
	
	public void setGiorno(String giorno) {
		this.giorno=giorno;
	}
	
	public void setDateTime(int dateTime) {
		this.dateTime=dateTime;
	}
	
	public void setTempMin(double tempMin) {
		this.TempMin=tempMin;
	}

	public void setTempMax(double tempMax) {
		this.TempMax=tempMax;
	}
	
	public void setPressione(int pressione) {
		this.Pressione=pressione;
	}
	
	public void setTemp(double temp) {
		this.TempPercepita=temp;
	}
	
	public void setDataLettura(String dataLettura) {
		this.dataLettura=dataLettura;
	}
}	
