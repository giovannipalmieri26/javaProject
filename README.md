# <div align="center"> RISTORANTE </div>

> "Ristorante" è un programma che si occupa della gestione di un ristorante volta alla prenotazione da parte di clienti. Dopo aver creato un account, il cliente ha la possibilità di prenotare il tavolo all'aperto o al chiuso in base alle conduzioni meteorologiche della giornata, e scegliere cosa mangiare nel menù proposto dal ristorante. Ovviamente il cliente può consultare le condizioni metereologiche del giorno in cui desidera prenotare il tavolo. 
> Il software utilizzato per la realizzazione del programma è [Postman](https://www.postman.com/) che permette di effettuare le varie operazioni precedentemente dette tramite la porta "localhost:8080". Di seguito elenchiamo le rotte che devono essere inserite dopo la porta, per eseguire le varie chiamate.
***
## <div align="center"> PATH PER IL PROFILO DELL'UTENTE

TIPO |ROTTA|INPUT|DESCRIZIONE
------ | ---|----|----------
POST|/creaAccount|"user" <br> "pass"|Crea un account inserendo username e password
POST|/login|"user" <br> "pass"|Accedi al tuo account inserendo username e password
GET|/logout| |Disconnette il proprio account
GET|/listaAccount| |Genera la lista di account presenti nel database 
GET|/previsioni| |Visualizza le condizioni metereologiche
POST|/prenota|"antipasto" <br> "primo" <br> "secondo" <br> "dolce"|Permette all'utente di prenotare
POST|/ristorante|"posto" <br> "nome"|Mostra tutte le informazioni relative al ristorante
GET|/visualizzaTavoli| |Visualizza la lista dei tavoli prenotati

***
- ## Diagramma dei casi d'uso    
- ## Diagramma delle classi
  
    Package|Classi
    -------|------
    api|UtilizzoApi;
    controller|SimpleRestController;
    interfacce|Oggetto;
    it.univpm.demo|DemoApplication;
    liste|Lista; ListaTavoli;
    luogo|Previsioni; 
    prenotazioni|Prenotazioni;
    registrazione|Account; Database;
    ristorante|Piatto; Ristorante; Statistiche; Tavolo;
  
- ## Diagramma delle sequenze
***
## Eccezioni 
***
## TEST
+ Sui Login
  + Accesso effettuato
  + Credenziali sbagliate
+ Sulle previsioni
  + Previsioni attuali
  + Previsioni non disponibili
***
## <div align="center">API utilizzati<div align="center">

Nome|Url|Descrizione
----|--|----
openweathermap|http://api.openweathermap.org/data/2.5/forecast?q=Ancona&appid=1954a4f8ba28a99259fbfcc0e65df65c|Utilizzato per l'acquisizione del meteo in tempo reale
edamam food|https://api.edamam.com/api/food-database/v2/parser|Utilizzato per i cibi da usare nel menù

***
## Tool e Librerie da istallare:
* [JSON simple](https://code.google.com/archive/p/json-simple/)
* [Spring Inizializr](https://start.spring.io/)
* [Spring Tool 4](https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4?mpc=true&mpc_state=)
* [Maven Integration for Eclipse](https://marketplace.eclipse.org/content/maven-integration-eclipse-luna-and-newer?mpc=true&mpc_state=)

***
## Contatti:
Programmatore | E-mail | Contributo
--------------|--------|-----------
Giovanni Palmieri|s1092410@studenti.univpm.it| <div align="center"> 55% </div>
Giorgio Olivieri|s1092407@studenti.univpm.it| <div align="center"> 35% </div>
Mattia Sisi|s1093418@studenti.univpmit| <div align="center"> 10% </div>

***
