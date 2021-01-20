package model;

/**
 * Classe che rappresenta i dati su cui è stato calcolato l'errore delle misurazioni e l'errore stesso.
 * @author Marco Pasquale Martino.
 *
 */
public class ModelError {
	/**
	 * Indica il nome della città.
	 */
	private String nome;
	/**
	 * Indica la data in cui è stata effettuata la lettura.
	 */
	private String dataLettura;
	/**
	 * Indica il giorno a cui si riferiscono i dati.
	 */
	private String giornoPrevisto;
	/**
	 * Indica l'errore percentuale tra i dati previsti e quelli effettivi.
	 */
	private String erroreLettura;
	/**
	 * Indica la temperatura massima prevista.
	 */
	private double tempMaxPrevista;
	/**
	 * Indica la temperatura massima effettiva.
	 */
	private double tempMaxCorretta;
	/**
	 * Indica la temperatura minima prevista.
	 */
	private double tempMinPrevista;
	/**
	 * Indica la temperatura minima effettiva.
	 */
	private double tempMinCorretta;
	/**
	 * Indica la temperatura percepita prevista.
	 */
	private double tempPercepitaPrevista;
	/**
	 * Indica la temperatura percepita effettiva.
	 */
	private double tempPercepitaCorretta;
	/**
	 * Indica la pressione prevista.
	 */
	private int pressionePrevista;
	/**
	 * Indica la pressione effettiva.
	 */
	private int pressioneCorretta;
	
	/**
	 * Costruttore che inizializza ogni attributo della classe ModelError.
	 * @param wet : oggetto di tipo Weather da cui prendere i dati "previsti";
	 * @param actual : oggetto di tipo Weather da cui prendere i dati "corretti";
	 * @param error : stringa contenente l'errore percentuale tra i dati previsti e quelli corretti.
	 */
	public ModelError(Weather wet, Weather actual, String error) {
		setErroreLettura(error);
		setNome(wet.getNome());
		setGiornoPrevisione(wet.getGiorno());
		setDataLettura(wet.getDataLettura());
		
		setTempMaxPrevista(wet.getTempMax());
		setTempMaxCorretta(actual.getTempMax());
		setTempMinPrevista(wet.getTempMin());
		setTempMinCorretta(actual.getTempMin());
		setTempPercepitaPrevista(wet.getTempPercepita());
		setTempPercepitaCorretta(actual.getTempPercepita());
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
	/**
	 * Metodo per impostare l'errore percentuale tra dati previsti e dati corretti.
	 * @param erroreLettura : errore percentuale tra i dati.
	 */
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
	public void setTempPercepitaPrevista(double tempPercepita) {
		tempPercepitaPrevista=tempPercepita;
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
	 * @param tempPercepita : temperatura percepita corretta.
	 */
	public void setTempPercepitaCorretta(double tempPercepita) {
		tempPercepitaCorretta=tempPercepita;
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
	public double getTempPercepitaPrevista() {
		return tempPercepitaPrevista;
	}

	/**
	 * Metodo che restituisce la temperatura percepita corretta.
	 * @return tempPercerpitaCorretta : temperatura percepita corretta.
	 */
	public double getTempPercepitaCorretta() {
		return tempPercepitaCorretta;
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
