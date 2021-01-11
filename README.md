# ProgettoOOP

Lo scopo del progetto è quello di implementare un servizio meteo che fornisca, in base al **Path** scelto, utilizzando i dati forniti dall'**API** di [OpenWeather](https://openweathermap.org/forecast5#geo5):
* Informazioni relative a temperatura e pressione di una determinata città, a partire dalle sue coordinate GPS;
* Statistiche relative alla temperatura massima, minima, percepita e media, in una città su base giornaliera, settimanale e mensile;
* Statistiche relative alla pressione media in una città su base giornaliera, settimanale e mensile;
* Statistiche relative alla quantità di previsione azzeccate in base ad una soglia d'errore.

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
/ | localhost:8080/ |

mostra latitudine e longitudine di alcune città quali:
- Ancona: 51.51, -0.13;
- Milano: 45.46, 9.19;
- Londra: 51.51, -0.13;
- Parigi: 48.85, 2.35;
- Berlino: 54.03, 10.45;

2)
Metodo | Path | 
---- | ---- | 
GET | localhost:8080/weather?lat={lat}&lon={lon}&cnt={cnt}" | 

fornisce i dati riguardante la città associata a quelle coordinate, per un quantitativo di giorni pari al numero indicato in *cnt* salvandoli, contemporaneamente, all'interno del file "weather.json" il quale funge da storico delle letture;

3)
Metodo | Path |
---- | ---- |
GET | localhost:8080/update |

effettua una lettura attuale delle previsioni relative ad una serie di città indicate all'interno di un file "city.json"; tale metodo verrà eseguito periodicamente ogni 3 ore.