# ProgettoOOP

Lo scopo del progetto è quello di implementare un servizio meteo che fornisca, in base al **Path** scelto, utilizzando i dati forniti dall'**API** di [OpenWeather](https://openweathermap.org/forecast5#geo5):
* Informazioni relative a temperatura e pressione di una determinata città, a partire dalle sue coordinate GPS;
* Statistiche relative alla temperatura massima, minima, percepita e media, in una città su base giornaliera, settimanale e mensile;
* Statistiche relative alla pressione media in una città su base giornaliera, settimanale e mensile.

# FUNZIONAMENTO

L'esecuzione del'servizio avviene tramite **"localhost:8080"** , sfruttando varie Path in base all'oprazione richiesta:

**PARAMETRI**
- *lat*: latitudine;
- *lon*: longitudine;
- *cnt*: intervallo di giorni (da 1 a 5) di cui prendere le previsioni

**NOTA**: i parametri riguardante latitudine e longitudine sono arrotondati per eccesso, alla seconda cifra decimale.


1)
Metodo | Path | 
---- | ---- | 
GET | localhost:8080/weather?lat={lat}&lon={lon}&cnt={cnt}" | 

fornisce i dati riguardante la città associata a quelle coordinate per un quantitativo di giorni pari al numero indicato in *cnt*;