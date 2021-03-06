# ProgettoOOP

Lo scopo del progetto è quello di implementare un servizio meteo che fornisca, in base al **Path** scelto, utilizzando i dati forniti dall'**API** di [OpenWeather](https://openweathermap.org/forecast5#geo5):
* Informazioni relative a temperatura e pressione di una determinata città, a partire dalle sue coordinate GPS;
* Statistiche relative alla temperatura massima, minima, percepita e media, in una città su base giornaliera, settimanale e mensile;
* Statistiche relative alla pressione media in una città su base giornaliera, settimanale e mensile;
* Statistiche relative alla quantità di previsione azzeccate in base ad una soglia d'errore.

# FUNZIONAMENTO

**Diagramma dei casi d'uso**

![Casi d'uso](./Diagrammi/useCase.jpg)

L'esecuzione del'servizio avviene tramite **"localhost:8080"** , sfruttando varie Path in base all'oprazione richiesta:

**PARAMETRI**
- *lat*: latitudine;
- *lon*: longitudine;
- *cnt*: intervallo di giorni (da 1 a 5) di cui prendere le previsioni

**NOTA**: i parametri riguardante latitudine e longitudine sono arrotondati per eccesso, alla seconda cifra decimale.

**1)**
Metodo | Path |
---- | ---- |
/ | localhost:8080/ |

mostra latitudine e longitudine di alcune città, tramite una pagina html, quali:
- Ancona: 51.51, -0.13;
- Milano: 45.46, 9.19;
- Londra: 51.51, -0.13;
- Parigi: 48.85, 2.35;
- Berlino: 54.03, 10.45;

**2)**
Metodo | Path | 
---- | ---- | 
GET | localhost:8080/weather?lat={lat}&lon={lon}&cnt={cnt}" | 

fornisce i dati riguardante la città associata a quelle coordinate, per un quantitativo di giorni pari al numero indicato in *cnt* salvandoli, contemporaneamente, all'interno del file "weather.json" il quale funge da storico delle letture;

**Esempio di riposta**

![Esempio di risposta /weather](./preview/weather.png)

**3)**
Metodo | Path |
---- | ---- |
GET | localhost:8080/update |

effettua una lettura attuale delle previsioni relative ad una serie di città indicate all'interno di un file "city.json"; tale metodo verrà eseguito periodicamente ogni 3 ore.

**Esempio di una parte della risposta**

![Esempio di una parte della risposta di /update](./preview/update.png)

**4)**
Metodo | Path |
---- | ---- |
GET | localhost:8080/error?lat={lat}&lon={lon}&cnt={cnt}&err={err}

confronta i dati relativi a temperatura e pressione calcolati in precedenza (con un massimo di 5 giorni, deciso dal cnt inserito dall'utente) con quelli presenti attualmente nella città definita dalle coordinate (lat e lon) inserite. Verranno restituiti all'utente solo i dati che hanno un errore totale uguale o inferiore a quello inserito dall'utente (err).

**Esempio di riposta**

![Esempio di risposta /error](./preview/error.png)

**5)**
Metodo | Path |
---- | ---- |
GET | localhost:8080/stats/pressione?lat={lat}&lon={lon}&cnt={cnt}

legge il file weather.json, ed estrapola i dati relativi al quantitativo di giorni specificati nel cnt, in questa chiamata il **cnt** dovrà essere inserito come stringa, ogni stringa preleverà un quantitativo diverso di dati su cui individuare la pressione massima e minima della città specificata.
Le stringhe consentite sono: 
- giornaliero, preleverà i dati di una giornata (8); 
- settimanale, preleverà i dati di una settimana (56); 
- mensile, preleverà i dati di un mese (240).

**Esempio di risposta**

![Esempio di risposta /stats/pressione](./preview/pressione.png)

**6)**
Metodo | Path |
---- | ---- |
GET | localhost:8080/stats/temperature/maxmin?lat={lat}&lon={lon}&cnt={cnt}&err={err}

legge il file weather.json, ed estrapola i dati relativi al quantitativo di giorni specificati nel cnt, in questa chiamata il **cnt** dovrà essere inserito come stringa, ogni stringa preleverà un quantitativo diverso di dati su cui individuare la temperatura massima e minima della città specificata.
Le stringhe consentite sono: 
- giornaliero, preleverà i dati di una giornata (8); 
- settimanale, preleverà i dati di una settimana (56); 
- mensile, preleverà i dati di un mese (240).

**Esempio di risposta**

![Esempio di risposta /stats/temperature/maxmin](./preview/maxmin.png)

**7)**

Metodo | Path |
---- | ---- |
GET | localhost:8080/stats/temperature/medie?lat={lat}&lon={lon}&cnt={cnt}

legge il file weather.json, ed estrapola i dati relativi al quantitativo di giorni specificati nel cnt, in questa chiamata il **cnt** dovrà essere inserito come stringa, ogni stringa preleverà un quantitativo diverso di dati su cui individuare la temperatura media e temepratura media percepita della città specificata.
Le stringhe consentite sono: 
- giornaliero, preleverà i dati di una giornata (8); 
- settimanale, preleverà i dati di una settimana (56); 
- mensile, preleverà i dati di un mese (240).

**Esempio di risposta**

![Esempio di risposta /stats/temperature/medie](./preview/medie.png)

# DIAGRAMMI UML

**Diagrammi delle classi**

- Package service

![packageService](./Diagrammi/ClassDiagrams/service.png)

- Package model

![packageModel](./Diagrammi/ClassDiagrams/model.png)

- Package parser

![packageParser](./Diagrammi/ClassDiagrams/parser.png)

- Package filtri

![packageFiltri](./Diagrammi/ClassDiagrams/filtri.png)

- Package errors

![packageErrors](./Diagrammi/ClassDiagrams/errors.png)

**Diagramma di Sequenza**

- localhost:8080/weather?{lat,lon,cnt}

![sequenceWeather](./Diagrammi/Sequenza/weatherSequence.jpg)

- localhost:8080/update

![sequenceUpdate](./Diagrammi/Sequenza/updateSequence.jpg)

- localhost:8080/stats/temperature/maxmin?{lat,lon,cnt}

![sequenceStatsTempMaxMin](./Diagrammi/Sequenza/statsTempMaxMinSequence.jpg)

- localhost:8080/stats/temperature/medie?{lat,lon,cnt}

![sequenceStatsTempAvrg](./Diagrammi/Sequenza/statsTempAvrgSequence.jpg)

- localhost:8080/stats/pressione?{lat,lon,cnt}

![sequenceStatsPressione](./Diagrammi/Sequenza/statsPressSequence.jpg)

- localhost:8080/error?{lat,lon,cnt,err}

![sequenceError](./Diagrammi/Sequenza/errorSequence.jpg)

# AUTORI

- Massimo Mecarelli. Contributo: 50%
- Marco Pasquale Martino. Contributo: 50%
