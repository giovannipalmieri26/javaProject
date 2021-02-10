package controller;

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
	
	private Ristorante ristorante;
	private boolean accesso;
	private String user;
	
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
	public String test1(@RequestParam(name = "user")String username,@RequestParam(name = "pass")String password) {
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
	public String test2(@RequestParam(name = "user")String username,@RequestParam(name = "pass")String password) {
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
	public Database test3() {
		return this.ristorante.database;
	}

	/**
	 * Permette di vedere le previsioni per i prossimi giorni
	 * @return lista delle previsioni desiderate
	 */
	@GetMapping("/previsioni")
	public Lista<Previsioni> test6() {
		UtilizzoApi api = new UtilizzoApi();
		return api.valorizzaListaPrevisioni();
	}
	
	/**
	 * Permette all'utente di prenotare qualsiasi cosa a eccezione degli ombrelloni all'interno
	 * dello chalet inserendo la cosa desiderata in richiesta
	 * Controlla in ogni lista dello chalet se la richiesta è presente
	 * @param richiesta oggetto richiesto dall'utente
	 * @param nome Nome dell'utente
	 * @param posti Posti se ad esempio si prenota il tavolo altrimenti 1 è il valore di default
	 * @return Esito della prenotazione
	 * @throws NumberFormatException
	 */
	@PostMapping("/prenota")
	public String test7(@RequestParam(name = "richiesta")String richiesta,@RequestParam(name = "nome",defaultValue = "")String nome){
		if (this.accesso) nome = this.user;
		if(richiesta.equals(nome)) return ("Prenotazione riuscita con successo!");
		return ("Prenotazione non riuscita. Riprova!");
	}
	
	/**
	 * @return restituisce un JSONObject contenente tutte le informazioni relative al ristorante
	 */
	@GetMapping("/ristorante")
	public Ristorante test9() {
		return this.ristorante;
	}
	
	/**
	 * Consente di fare il logout dal sever del ristorante
	 * @return ritorna l'esito
	 */
	@GetMapping("/logout")
	public String test12() {
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
	public String test14 (){
		return this.ristorante.listaTavoli.toString();
	}
}
