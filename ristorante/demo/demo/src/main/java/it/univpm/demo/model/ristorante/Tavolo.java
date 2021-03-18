package it.univpm.demo.model.ristorante;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Vector;

import it.univpm.demo.model.interfacce.Oggetto;
import it.univpm.demo.model.luogo.Previsioni;
import it.univpm.demo.model.prenotazioni.*;
import it.univpm.demo.model.api.UtilizzoApi;

/**
 * @author Giovanni
 * @author Giorgio 
 * @author Mattia
 * 
 *Classe che contiene le informazioni di un tavolo
 */

public class Tavolo implements Oggetto {

	/**
	 *Variabile che contiene la data della prenotazione del tavolo
	 */
	private LocalDate data;
	
	/**
	 * String che contiene il nome del cliente che ha effettuato la prenotazione del tavolo
	 */
	private String nome;

	/**
	 * Oggetto che contiene le previsioni meteo per la giornata in cui il tavolo è stato prenotato
	 */
	private Previsioni previsione;
	
	/**
	 * Vector che contiene le prenotazioni effetuate
	 */
	private Vector<String> listaPrenotazioni= new Vector <String>();

	/*
	 * Oggetto che rappresenta la prenotazione del tavolo
	 */
	private Prenotazioni prenotazione;

	/*
	 * Stato della prenotazione del tavolo: true ovvero tavolo prenotato all'esterno se le condizioni meteo lo permettono, altrimenti false
	 */
	private boolean stato;

	/**
	 * Costruttore della classe Tavolo
	 * @param anno della prenotazione del tavolo
	 * @param mese della prenotazione del tavolo
	 * @param giorno della prenotazione del tavolo
	 */
	public Tavolo (int anno, int mese, int giorno) {
		try {
			this.data =  LocalDate.of(anno,mese,giorno);
		}
		catch(DateTimeException e) {
		}
		this.setPrevisioni();
	}
	
	/**
	 * Restituisce la data della prenotazione del tavolo
	 * @return ritorna la data della prenotazione del tavolo
	 */
	public LocalDate getData() {
		return this.data;
	}	
	
	/**
	 * Setta il nome di chi ha effettuato la prenotazione del tavolo
	 * @param nome di chi ha effettuato la prenotazione del tavolo
	 */
	public void setTipo(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Restituisce la stringa contenente di chi ha effettuato la prenotazione del tavolo
	 * @return ritorna la stringa con il nome di chi ha effettuato la prenotazione del tavolo
	 */
	public String getTipo() {
		 return this.nome; 
	}
	
	/**
	 * Effettua un controllo sulle condizioni meteo
	 *@return ritorna true ovvero tavolo prenotato all'esterno se le condizioni meteo lo permettono altrimenti false
	 */
	public boolean getStato() {
		if(this.previsione.getCondizioni()) {
			this.stato = true;
			return this.stato;
		}
		this.stato = false;
		return this.stato;
	}
	
	/**
	 * Restituisce il vettore di strighe delle prenotazioni del tavolo effettuate
	 * @return Vector contenente le prenotazioni del tavolo effettuate
	 */
	public Vector<String> getListaPrenotazioni(){
		return this.listaPrenotazioni;
	}
	
	/**
	 * Consente di aggiungere una prenotazione del tavolo a partire dal nome passato come parametro
	 * @param nome della prenotazione del tavolo che si vuole aggiungere
	 */
	public void aggiungiPrenotazione(String nome) {
		this.nome = nome;
		listaPrenotazioni.add(this.nome);
	}
	
	/**
	 *Trasforma il tavolo in string
	 * @return String della prenotazione del tavolo
	 */
	public String toString () {
		return("Prenotazione effettuata con successo per il:\t" + this.data + "\n");
	}
	
	/**
	 * Restituisce la previsione relativa alla prenotazione del tavolo effettuata
	 * @return ritorna l'oggetto che contiene la previsione meteo relativa alla prenotazione del tavolo effettuata
	 */
	public Previsioni getPrevisione() {
		return this.previsione;
		
	}
	
	/**
	 * Chiama l'api e valorizza l'oggetto Previsioni
	 */
	private void setPrevisioni() {
		UtilizzoApi api = new UtilizzoApi();
		try {
			this.previsione = api.valorizzaPrevisione(this.data);
		}
		catch(NullPointerException e) {
			this.previsione.setCondizioni("Previsioni non disponibili");
		}
	}

	/**
	 * Imposta lo stato del tavolo
	 * @param stato del tavolo
	 */
	public void setStato(boolean stato) {
		this.stato = stato;
		
	}

	/**
	 * Restituisce la prenotazione del tavolo effettuata
	 * @return prenotazione del tavolo effettuata
	 */
	public Prenotazioni getPrenotazione() {
		return prenotazione;
	}

	/**
	 * Setta la prenotazione del tavolo
	 * @param prenotazione da settare
	 */
	public void setPrenotazione(Prenotazioni prenotazione) {
		this.prenotazione = prenotazione;
	}
}
