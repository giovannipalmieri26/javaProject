package controller;

import java.time.DateTimeException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import model.api.*;
import model.liste.*;
import model.luogo.*;
import model.ristorante.*;
import model.registrazione.*;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * 
 * Classe che gestisce le chiamate Postman
 *
 */

@RestController
public class simpleRestController {
	
	private UtilizzoApi api;
	private Ristorante ristorante;
	private boolean accesso;
	private String user;
	private Piatto piatto;
	private Tavolo tavolo;
	
	/**
	 * Costruttore che crea il ristorante, 
	 * setta l'accesso a false perchè non è stato ancora effettuato il login
	 * e quindi lo username dell'utente è vuoto 
	 */
	public simpleRestController() {
		this.ristorante = new Ristorante();
		this.accesso = false;
		this.user =  "";
	}
	
	/**
	 * Permette ad un utente di creare un account
	 * @param username username per l'account
	 * @param password password dell'account
	 * @return esito della creazione
	 */
	@PostMapping("/creaAccount")
	public String crea(@RequestParam(name = "user")String username,@RequestParam(name = "pass")String password) {
		if(this.ristorante.database.creaAccount(username, password)) {
			return "Account creato";
			}
		else return "Username in uso";
	}
	
	/**
	 * Consente di effettuare il login per poi eseguire le azioni desiderate
	 * Facendo una prenotazione in seguito nel nome della prenotazione verra inserito l'user
	 * @param username usename dell'utente
	 * @param password password dell'account
	 * @return Esito del login
	 */
	@PostMapping("/login")
	public String login(@RequestParam(name = "user")String username,@RequestParam(name = "pass")String password) {
		if(this.ristorante.database.login(username, password)) {
			this.accesso = true;
			this.user = username;
			return ("Accesso effettuato");
			}
		else return ("Credenziali sbagliate");
	}
	
	/**
	 * Permette al proprietario di vedere la lista degli account
	 * @return La lista degli Account
	 */
	@GetMapping("/listaAccount")
	public Database lista() {
		return this.ristorante.database;
	}

	/**
	 * Permette di vedere le previsioni per i prossimi giorni
	 * @return lista delle previsioni desiderate
	 */
	@GetMapping("/previsioni")
	public Lista<Previsioni> listaPrev() {
		UtilizzoApi api = new UtilizzoApi();
		return api.valorizzaListaPrevisioni();
	}
	
	/**
	 * Consente al cliente di prenotare un tavolo
	 * Può generare un eccezione se la data non esiste
	 * @param nome Nome del cliente
	 * @param anno Anno in cui si svolge 
	 * @param mese Mese in cui si svolge 
	 * @param giorno Giorno in cui si svolge 
	 * @return Esito della creazione
	 */
	@PostMapping("/creaEvento")
	public String test4(@RequestParam(name = "nome",defaultValue = "")String nome,
			@RequestParam(name = "anno",defaultValue = "2020")int anno, 
			@RequestParam(name = "mese",defaultValue = "12")int mese,
			@RequestParam(name = "giorno")int giorno) throws DateTimeException{
		Tavolo tavolo;
		try {
	tavolo = new Tavolo(nome, anno, mese, giorno);
	}
		catch(DateTimeException e){
			return ("Informazioni inesistenti");
		}
		aggiungiPrenotazione.(tavolo);
	return ("Evento creato");
	}
	
	/**
	 * Permette all'utente di prenotare 
	 * @param richiesta oggetto richiesto dall'utente
	 * @param nome Nome dell'utente
	 * @param posti Posti se ad esempio si prenota il tavolo altrimenti 1 è il valore di default
	 * @return Esito della prenotazione
	 * @throws NumberFormatException
	 */
	@PostMapping("/prenota")
	public String prenotazione(@RequestParam(name = "antipasto")String antipasto, @RequestParam(name = "primo") String primo, @RequestParam(name = "antipasto")String secondo, @RequestParam(name = "antipasto")String dolce, @RequestParam(name = "nome",defaultValue = "")String nome) {
		if (this.accesso) nome = this.user;
			if(antipasto != "") {
				piatto = api.valorizzaPiatto(antipasto);
				if(piatto.getValore() <= 100) piatto.setValore(piatto.getValore()/10);
				else piatto.setValore(piatto.getValore()/100 + 5);
			}
			if(primo != "") {
				piatto = api.valorizzaPiatto(primo);
				if(piatto.getValore()/100 <= 1) piatto.setValore(piatto.getValore()/100 + 7);
				else piatto.setValore(piatto.getValore()/100 + 5);
			}
			if(secondo != "") {
				piatto = api.valorizzaPiatto(secondo);
				if(piatto.getValore()/100 <= 1) piatto.setValore(piatto.getValore()/100 + 7);
				else piatto.setValore(piatto.getValore()/100 + 5);
			}
			if(dolce != ""){
				piatto = api.valorizzaPiatto(dolce);
				if(piatto.getValore() <= 100) piatto.setValore(piatto.getValore()/10);
				else piatto.setValore(piatto.getValore()/100 + 5);
			}
		if(nome.equals(nome) && (main.equals("Clouds")||main.equals("Rain")) return ("Prenotazione riuscita con successo! Il tavolo prenotato si trova all'esterno");
		if(nome.equals(nome) && ) return ("Prenotazione riuscita con successo! Il tavolo prenotato si trova all'interno");
		return ("Prenotazione non riuscita. Riprova!");
	}
	
	/**
	 * @return restituisce un JSONObject contenente tutte le informazioni relative al ristorante
	 */
	@GetMapping("/ristorante")
	public Ristorante ristorante() {
		return this.ristorante;
	}
	
	/**
	 * Consente di fare il logout dal sever del ristorante
	 * @return ritorna l'esito
	 */
	@GetMapping("/logout")
	public String logout() {
		if(!this.accesso) return ("Non sei loggato");
		else {
			this.accesso = false;
			this.user = "";
			return ("Uscito con successo");
			}	
		}
	
	/*
	 * @return mostra la lista dei tavoli prenotati
	 */
	@GetMapping("/visualizzaTavoli")
	public String tavoli() {
		return this.ristorante.listaTavoli.toString();
	}
}
