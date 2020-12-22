# ProgettoOOP

Lo scopo del progetto è quello di implementare un servizio meteo che fornisca, in base all'API scelta:
* Informazioni relative a temperatura e pressione di una determinata città, a partire dalle sue coordinate GPS;
* Statistiche relative alla temperatura massima, minima, percepita e media, in una città su base giornaliera, settimanale e mensile;
* Statistiche relative alla pressione media in una città su base giornaliera, settimanale e mensile.

# FUNZIONAMENTO

L'esecuzione del'servizio avviene tramite **"localhost:8080"** , sfruttando varie Path in base all'oprazione richiesta:
1)
	 Metodo | Path                   
	 ------ | ---------------------- 
	 GET    | localhost:8080/weather 