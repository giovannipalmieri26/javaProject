package it.univpm.demo.controller;

import java.time.DateTimeException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.demo.eccezioni.*;
import it.univpm.demo.model.api.*;
import it.univpm.demo.model.liste.Lista;
import it.univpm.demo.model.liste.ListaPrenotazioni;
import it.univpm.demo.model.luogo.Previsioni;
import it.univpm.demo.model.prenotazioni.Prenotazioni;
import it.univpm.demo.model.registrazione.Database;
import it.univpm.demo.model.ristorante.*;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * 
 * Classe che gestisce le chiamate Postman
 *
 */

@RestController
public class SimpleRestController {
	
	
	private UtilizzoApi api = new UtilizzoApi();
	private Ristorante ristorante;
	private boolean accesso;
	private boolean ordine;
	private String user;
	private Piatto antipasto;
	private Piatto primo;
	private Piatto secondo;
	private Piatto dolce;
	private String nome;
	private Lista<Tavolo> listaTavoli = new Lista<Tavolo>();
	
	/**
	 * Costruttore che crea il ristorante, 
	 * setta l'accesso a false perchè non è stato ancora effettuato il login
	 * e quindi lo username dell'utente è vuoto 
	 */
	public SimpleRestController() {
		this.ristorante = new Ristorante();
		this.accesso = false;
		this.user =  "";
	}
	
	/**
	 * Permette ad un utente di creare un account
	 * @param username username per l'account
	 * @param password password dell'account
	 * @return String con esito della creazione
	 */
	@PostMapping("/creaAccount")
	public String crea(@RequestParam(name = "user", defaultValue = "")String username,@RequestParam(name = "pass", defaultValue = "")String password) {
		if(this.ristorante.getDatabase().creaAccount(username, password)) {
			return "Account creato con successo!";
			}
		else return "Username già in uso! Riprova";
	}
	
	/**
	 * Consente di effettuare il login per poi eseguire le azioni desiderate
	 * @param username usename dell'utente
	 * @param password password dell'account
	 * @return String con esito del login
	 */
	@PostMapping("/login")
	public String login(@RequestParam(name = "user", defaultValue = "")String username,@RequestParam(name = "pass", defaultValue = "")String password) {
		if(this.ristorante.getDatabase().login(username, password)) {
			this.accesso = true;
			this.user = username;
			return ("Login effettuato con successo!");
			}
		else return ("Credenziali sbagliate! Riprova");
	}
	
	/**
	 * Questa rotta è consentita solo al proprietario del ristorante e permette di visualizzare la lista degli account.
	 * Per accedervi è infatti necessaria la seguente chiave di accesso: Info2021
	 * @param chiave di accesso
	 * @return Database con la lista degli Account
	 */
	@GetMapping("/listaAccount")
	public Database lista(@RequestParam(name = "chiave", defaultValue = "-")String chiave) {
		if((chiave.equals("Info2021"))) return this.ristorante.getDatabase();
		return null;
	}
	
	/**
	 * Permette di visualizzare la lista delle prenotazioni 
	 * @param nome di chi prenota
	 * @param stato inserire "esterno" se si vuole visualizzare le prenotazioni effettuate all'aperto, 
	 * inserire "interno" se si vuole visualizzare le prenotazioni all'interno
	 * @return String con la lista delle prenotazioni
	 */
	@GetMapping("/listaPrenotazioni")
	public ListaPrenotazioni<Prenotazioni> listaPrenotazioni(@RequestParam(name = "nome", defaultValue = "-")String nome,
			@RequestParam(name = "stato", defaultValue = "-")String stato) {
		if(this.accesso) {
			ListaPrenotazioni<Prenotazioni> lista = new ListaPrenotazioni<Prenotazioni>();
			if((nome.equals("-"))&&(stato.equals("-"))) return(ristorante.getListaPrenotazioni());
			if(!nome.equals("-")&&(stato.equals("-"))) {
				if(ristorante.getListaPrenotazioni().getLista().size() == 0) return(ristorante.getListaPrenotazioni()); 
				for( Prenotazioni Elemento : ristorante.getListaPrenotazioni().getLista()) {
					if(Elemento.nome.equals(nome)) lista.aggiungi(Elemento);
				}
				return lista;
			}
			if(nome.equals("-")&&(!stato.equals("-"))) {
				boolean stato2;
				if(stato.equals("esterno")) stato2 = true;
				else if(stato.equals("interno")) stato2 = false;
				else return null;
				if(ristorante.getListaPrenotazioni().getLista().size() == 0) return(ristorante.getListaPrenotazioni());
				for( Prenotazioni Elemento : ristorante.getListaPrenotazioni().getLista()) {
					if(Elemento.getOrdine() == stato2) lista.aggiungi(Elemento);
				}
				return lista;
			}
			if(!nome.equals("-")&&(!stato.equals("-"))) {
				boolean stato2;
				if(stato.equals("esterno")) stato2 = true;
				else stato2 = false;
				if(ristorante.getListaPrenotazioni().getLista().size() == 0) return(ristorante.getListaPrenotazioni());
				for( Prenotazioni Elemento : ristorante.getListaPrenotazioni().getLista()) {
					if((Elemento.getOrdine() == stato2)&&(Elemento.nome.equals(nome))) lista.aggiungi(Elemento);
				}
				return lista;
			}
		}
		return null;
	}

	/**
	 * Permette di vedere le previsioni per i prossimi giorni
	 * @param giorni di cui si vuole controllare le previsioni
	 * @return String con la lista delle previsioni desiderate
	 */
	@GetMapping("/previsioni")
	public Lista<Previsioni> listaPrev(@RequestParam(name = "giorni", defaultValue = "5")int giorni) {
		UtilizzoApi api = new UtilizzoApi();
		if(giorni <= api.giorniMax) {
		api.scansioniTotali *= giorni;
		return api.valorizzaListaPrevisioni();
		}
		return null;
	}
		
	
	/**
	 * Consente al cliente di prenotare un tavolo
	 * Può generare un'eccezione se la data non esiste
	 * @param anno Anno in cui si svolge 
	 * @param mese Mese in cui si svolge 
	 * @param giorno Giorno in cui si svolge 
	 * @return String con esito della creazione
	 * @throws DateTimeException eccezione per l'inserimento errato di date
	 * @throws EccezioneData eccezione per l'inserimento errato di date
	 */
	@PostMapping("/prenotaTavolo")
	public String prenotaTavolo(@RequestParam(name = "anno", defaultValue = "2021")int anno, 
			@RequestParam(name = "mese", defaultValue = "03")int mese,
			@RequestParam(name = "giorno")int giorno) throws DateTimeException, EccezioneData {
		if(this.accesso) {
			try {
				if((mese > 12) || (giorno > 31)) throw new EccezioneData();
				Tavolo tavolo = new Tavolo(anno, mese, giorno);
				ordine = tavolo.getPrevisione().getCondizioni();
				nome = user;
				this.listaTavoli.aggiungi(tavolo);
				if(tavolo.getPrevisione().getCondizioni()) return (tavolo.toString()+ "Il tavolo si troverà all'esterno");	
				return (tavolo.toString()+ "Il tavolo si troverà all'interno");	
			}
			catch(DateTimeException e){
				return ("Informazioni inesistenti");
			}
			
		}
		return("Azione non riuscita! Effettuare il login per prenotare un tavolo");
	}
	
	/**
	 * Permette all'utente di prenotare il Menu inserendo i cibi desiderati. Se si vuole inserire un cibo in cui è presente la spaziatura, 
	 * ad esempio "
	 * @param antipasto che si desidera ordinare
	 * @param primo che si desidera ordinare
	 * @param secondo che si desidera ordinare
	 * @param dolce che si desidera ordinare
	 * @return String che restituisce il Menu prenotato
	 * @throws EccezioneMenu eccezione per cibi non presenti nel menu
	 */
	@PostMapping("/prenotaMenu")
	public String prenotaMenu(@RequestParam(name = "antipasto", defaultValue = "")String antipasto, 
			@RequestParam(name = "primo", defaultValue = "") String primo, 
			@RequestParam(name = "secondo", defaultValue = "")String secondo, 
			@RequestParam(name = "dolce", defaultValue = "")String dolce) throws EccezioneMenu {
		if(this.accesso) {
			if(user == nome) {
				this.antipasto = api.valorizzaPiatto(antipasto);
				if((this.antipasto.getNome() == null) && (this.antipasto.getPrezzo() == 0.0)) throw new EccezioneMenu();
				if(this.antipasto.getNome() != "-") {
					if(this.antipasto.getPrezzo() <= 100) this.antipasto.setPrezzo(this.antipasto.getPrezzo()/10);
					else this.antipasto.setPrezzo(this.antipasto.getPrezzo()/100 + 5);
				}
				this.primo = api.valorizzaPiatto(primo);
				if((this.primo.getNome() == null) && (this.primo.getPrezzo() == 0.0)) throw new EccezioneMenu();
				if(this.primo.getNome() != "-") {
					if(this.primo.getPrezzo()/100 <= 1) this.primo.setPrezzo(this.primo.getPrezzo()/100 + 7);
					else this.primo.setPrezzo(this.primo.getPrezzo()/100 + 5);
				}
				this.secondo = api.valorizzaPiatto(secondo);
				if((this.secondo.getNome() == null) && (this.secondo.getPrezzo() == 0.0)) throw new EccezioneMenu();
				if(this.secondo.getNome() != "-") {
					if(this.secondo.getPrezzo()/100 <= 1) this.secondo.setPrezzo(this.secondo.getPrezzo()/100 + 7);
					else this.secondo.setPrezzo(this.secondo.getPrezzo()/100 + 5);
				}
				this.dolce = api.valorizzaPiatto(dolce);
				if((this.dolce.getNome() == null) && (this.dolce.getPrezzo() == 0.0)) throw new EccezioneMenu();
				if(this.dolce.getNome() != "-"){
					if(this.dolce.getPrezzo() <= 100) this.dolce.setPrezzo(this.dolce.getPrezzo()/10);
					else this.dolce.setPrezzo(this.dolce.getPrezzo()/100 + 5);
				} 
				Prenotazioni prenotazione = new Prenotazioni(user, this.antipasto, this.primo, this.secondo, this.dolce, ordine);
				prenotazione.numero = ristorante.getListaPrenotazioni().getLista().size();
				ristorante.getListaPrenotazioni().aggiungi(prenotazione);
				ristorante.setContoMedio(ristorante.getListaPrenotazioni());
				return("Prenotazione effettuata! ");
			}
			return("Prima occorre prenotare il tavolo!");
		}
		return("Prenotazione non effettuata! Effettuare il login");
	}

	/**
	 * Questa rotta è consentita solo al proprietario del ristorante e  restituisce un JSONObject con tutte le informazioni relative al ristorante.
	 * Per accedervi è infatti necessaria la seguente chiave di accesso: Info2021
	 * @param  chiave di accesso
	 * @return Ristorante contenente tutte le informazioni relative al ristorante
	 */
	@GetMapping("/ristorante")
	public Ristorante ristorante(@RequestParam(name = "chiave", defaultValue = "-")String chiave) {
		if(chiave.equals("Info2021")) {
			Ristorante ristorante2 = new Ristorante();
			ristorante2.setDatabase(this.ristorante.getDatabase());
			ristorante2.listaStats = this.ristorante.listaStats;
			this.ristorante.getListaPrenotazioni().setListaPrenotazioni(ristorante2.getListaPrenotazioni());
			ristorante2.setContoMedio(ristorante2.getListaPrenotazioni());
			return ristorante2;
		}
		return null;
	}
	
	/**
	 * Permette ad un utente di eliminare una prenotazione che ha effettuato
	 * @param numero numero della prenotazione
	 * @return Strin con esito dell'eliminazione
	 */
	@PostMapping("/elimina")
	public String elimina(@RequestParam(name = "numero")int numero) {
		boolean verifica = false;
		if(this.accesso) {	
			for(Prenotazioni Elemento : ristorante.getListaPrenotazioni().getLista()) {
				if(ristorante.getListaPrenotazioni().getLista().isEmpty()) return("Effettua una prenotazione");
				if(Elemento.nome == user) {
					verifica = true;
					if(ristorante.getListaPrenotazioni().getLista().indexOf(Elemento) == (numero)) {
						ristorante.getListaPrenotazioni().getLista().removeElementAt(numero);
						return("Eliminazione della prenotazione effettuata con successo!");
					}
				}
			}
			if(verifica) return("Reinserire il numero della prenotazione che si vuole eliminare");
			return("Effettua una prenotazione");
		}
		return("Reinserire il numero della prenotazione che si vuole eliminare");
	}
	
	/**
	 * Consente di fare il logout dal sever del ristorante
	 * @return String con esito del logout
	 */
	@GetMapping("/logout")
	public String logout() {
		if(!this.accesso) return ("Non sei loggato");
		else {
			this.accesso = false;
			this.user = "";
			return ("Uscito con successo!");
		}	
	}
}
