package model;

/**
 * Classe che rappresenta i dati su cui è stato calcolato l'errore delle misurazioni e l'errore stesso.
 * @author Marco Pasquale Martino.
 *
 */
public class ModelError {
	private String nome;
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
	
	public ModelError(Weather wet, Weather actual, String error) {
		this.erroreLettura=error;
		setNome(wet.getNome());
		setGiornoPrevisione(wet.getGiorno());
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

	/**
	 * Metodo per impostare il nome della città.
	 * @param nome : nome della città.
	 */
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	/**
	 * Metodo per impostare il giorno in cui è stata effettuata la lettura.
	 * @param dataLettura : giorno in cui è stata effettuata la lettura.
	 */
	public void setDataLettura(String dataLettura) {
		this.dataLettura=dataLettura;
	}
	

	/**
	 * Metodo per impostare il giorno della previsione in formato dd-MM-yyyy.
	 * @param giornoPrevisto : giorno in formato dd-MM-yyyy.
	 */
	public void setGiornoPrevisione(String giornoPrevisto) {
		this.giornoPrevisto=giornoPrevisto;
	}
	
	public void setErroreLettura(String erroreLettura) {
		this.erroreLettura=erroreLettura;
	}

	/**
	 * Metodo per impostare la temperatra massima prevista.
	 * @param tempMax : temperatura massima prevista.
	 */
	public void setTempMaxPrevista(double tempMax) {
		tempMaxPrevista=tempMax;
	}

	/**
	 * Metodo per impostare la temeperatura minima prevista.
	 * @param tempMin : temperatura minima prevista.
	 */
	public void setTempMinPrevista(double tempMin) {
		tempMinPrevista=tempMin;
	}
	
	/**
	 * Metodo per impostare la temperatura percepita prevista.
	 * @param tempPercepita : temperatura percepita prevista.
	 */
	public void setTempPercepitàPrevista(double tempPercepità) {
		tempPercepitàPrevista=tempPercepità;
	}
	
	/**
	 * Metodo per impostare la pressione prevista.
	 * @param pressione : pressione prevista.
	 */
	public void setPressionePrevista(int pressione) {
		pressionePrevista=pressione;
	}

	/**
	 * Metodo per impostare la temperatra massima corretta.
	 * @param tempMax : temperatura massima corretta.
	 */
	public void setTempMaxCorretta(double tempMax) {
		tempMaxCorretta=tempMax;
	}

	/**
	 * Metodo per impostare la temeperatura minima corretta.
	 * @param tempMin : temperatura minima corretta.
	 */
	public void setTempMinCorretta(double tempMin) {
		tempMinCorretta=tempMin;
	}

	/**
	 * Metodo per impostare la temperatura percepita corretta.
	 * @param tempPercepità : temperatura percepita corretta.
	 */
	public void setTempPercepitàCorretta(double tempPercepità) {
		tempPercepitàCorretta=tempPercepità;
	}

	/**
	 * Metodo per impostare la pressione corretta.
	 * @param pressione : pressione corretta.
	 */
	public void setPressioneCorretta(int pressione) {
		pressioneCorretta=pressione;
	}

	/**
	 * Metodo che restituisce il nome della città.
	 * @return nome : nome della città.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che restituisce il giorno in cui è stata fatta la lettura.
	 * @return dataLettura : giorno in cui è stata effettuata la lettura.
	 */
	public String getDataLettura() {
		return dataLettura;
	}
	
	/**
	 * Metodo che restituisce il giorno della previsione, in formato dd-MM-yyyy.
	 * @return giornoPrevisto : giorno della previsione.
	 */
	public String getGiornoPrevisto() {
		return giornoPrevisto;
	}
	
	/**
	 * Metodo che restitusice l'errore percentuale tra i dati previsti e quelli attuali.
	 * @return erroreLettura : errore percentuale tra i dati.
	 */
	public String getErroreLettura() {
		return erroreLettura;
	}

	/**
	 * Metodo che restituisce la temperatura massima prevista.
	 * @return tempMaxPrevista : temperatura massima prevista.
	 */
	public double getTempMaxPrevista() {
		return tempMaxPrevista;
	}

	/**
	 * Metodo che restituisce la temperatura massima corretta.
	 * @return tempMaxCorretta : temperatura massima corretta.
	 */
	public double getTempMaxCorretta() {
		return tempMaxCorretta;
	}
	
	/**
	 * Metodo che restitusice la temperatura minima prevista.
	 * @return tempMinPrevista : temperatura minima prevista.
	 */
	public double getTempMinPrevista() {
		return tempMinPrevista;
	}
	
	/**
	 * Metodo che restitusice la temperatura minima corretta.
	 * @return tempMinCorretta : temperatura minima corretta.
	 */
	public double getTempMinCorretta() {
		return tempMinCorretta;
	}

	/**
	 * Metodo che restituisce la temperatura percepita prevista.
	 * @return tempPercerpitaPrevista : temperatura percepita prevista.
	 */
	public double getTempPercepitàPrevista() {
		return tempPercepitàPrevista;
	}

	/**
	 * Metodo che restituisce la temperatura percepita corretta.
	 * @return tempPercerpitaCorretta : temperatura percepita corretta.
	 */
	public double getTempPercepitàCorretta() {
		return tempPercepitàCorretta;
	}

	/**
	 * Metodo che restituisce la pressione prevista.
	 * @return pressione : pressione prevista.
	 */
	public int getPressionePrevista() {
		return pressionePrevista;
	}

	/**
	 * Metodo che restituisce la pressione corretta.
	 * @return pressioneCorretta : pressione corretta.
	 */
	public int getPressioneCorretta() {
		return pressioneCorretta;
	}
}
