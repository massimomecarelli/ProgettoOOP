package model;

public class modelError {
	private String dataLettura;
	private String giornoPrevisto;
	private String erroreLettura;
	private double tempMaxPrevista;
	private double tempMaxCorretta;
	private double tempMinPrevista;
	private double tempMinCorretta;
	private double tempPercepitàPrevista;
	private double tempPercepitàCorretta;
	private int pressionePrevista;
	private int pressioneCorretta;
	
	public modelError(Weather wet, Weather actual, String error) {
		this.erroreLettura=error;
		setGiornoPrevisione(wet.getGiorno());
		System.out.println(wet.getGiorno());
		setDataLettura(wet.getDataLettura());
		
		setTempMaxPrevista(wet.getTempMax());
		setTempMaxCorretta(actual.getTempMax());
		setTempMinPrevista(wet.getTempMin());
		setTempMinCorretta(actual.getTempMin());
		setTempPercepitàPrevista(wet.getTempPercepita());
		setTempPercepitàCorretta(actual.getTempPercepita());
		setPressionePrevista(wet.getPressione());
		setPressioneCorretta(actual.getPressione());
	}
	
	public void setDataLettura(String dataLettura) {
		this.dataLettura=dataLettura;
	}
	
	public void setGiornoPrevisione(String giornoPrevisto) {
		this.giornoPrevisto=giornoPrevisto;
	}
	
	public void setErroreLettura(String erroreLettura) {
		this.erroreLettura=erroreLettura;
	}
	
	public void setTempMaxPrevista(double tempMax) {
		tempMaxPrevista=tempMax;
	}
	
	public void setTempMinPrevista(double tempMin) {
		tempMinPrevista=tempMin;
	}
	
	public void setTempPercepitàPrevista(double tempPercepità) {
		tempPercepitàPrevista=tempPercepità;
	}
	
	public void setPressionePrevista(int pressione) {
		pressionePrevista=pressione;
	}
	
	public void setTempMaxCorretta(double tempMax) {
		tempMaxCorretta=tempMax;
	}
	
	public void setTempMinCorretta(double tempMin) {
		tempMinCorretta=tempMin;
	}
	
	public void setTempPercepitàCorretta(double tempPercepità) {
		tempPercepitàCorretta=tempPercepità;
	}
	
	public void setPressioneCorretta(int pressione) {
		pressioneCorretta=pressione;
	}
	
	public String getDataLettura() {
		return dataLettura;
	}
	
	public String getgiornoPrevisto() {
		return giornoPrevisto;
	}
	
	public String getErroreLettura() {
		return erroreLettura;
	}
	
	public double getTempMaxPrevista() {
		return tempMaxPrevista;
	}
	
	public double getTempMaxCorretta() {
		return tempMaxCorretta;
	}
	
	public double getTempMinPrevista() {
		return tempMinPrevista;
	}
	
	public double getTempMinCorretta() {
		return tempMinCorretta;
	}
	
	public double getTempPercepitàPrevista() {
		return tempPercepitàPrevista;
	}
	
	public double getTempPercepitàCorretta() {
		return tempPercepitàCorretta;
	}
	
	public int getPressionePrevista() {
		return pressionePrevista;
	}
	
	public int getPressioneCorretta() {
		return pressioneCorretta;
	}
}
