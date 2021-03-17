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
 *Classe che contiene le informazioni della prenotazione di un tavolo
 */

public class Tavolo implements Oggetto {

	/**
	 *Variabile che contiene la data della prenotazione del tavolo
	 */
	private LocalDate data;
	
	/**
	 * String che contiene il nome del cliente
	 */
	private String nome;

	/**
	 * Oggetto che contiene le previsioni meteo per quella giornata
	 */
	private Previsioni previsione;
	
	/**
	 * Vector che contiene  le prenotazioni 
	 */
	private Vector<String> listaPrenotazioni= new Vector <String>();

	/*
	 * Oggetto che rappresenta la prenotazione del tavolo
	 */
	private Prenotazioni prenotazione;

	private boolean stato;

	/**
	 * Costruttore della classe Tavolo
	 * @param anno anno della prenotazione del tavolo
	 * @param mese  mese della prenotazione del tavolo
	 * @param giorno giorno della prenotazione del tavolo
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
	 * @return ritorna la data dell'evento
	 */
	public LocalDate getData() {
		return this.data;
	}	
	
	/**
	 * @param nome attribuisce il nome del tavolo
	 */
	public void setTipo(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return ritorna la stringa con il nome del tavolo
	 */
	public String getTipo() {
		 return this.nome; 
	}
	
	/**
	 * Effettua un controllo sulle condizioni meteo
	 *@return ritorna true ovvero tavolo disponibile all'esterno se le condizioni meteo lo permettono
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
	 * @return ritorna il vector che contiene le prenotazioni
	 */
	public Vector<String> getListaPrenotazioni(){
		return this.listaPrenotazioni;
	}
	
	/**
	 *Aggiunge una prenotazione prendendo come parametro il nome
	 */
	public void aggiungiPrenotazione(String nome) {
		this.nome = nome;
		listaPrenotazioni.add(this.nome);
	}
	
	/**
	 *Trasforma il tavolo in string
	 */
	public String toString () {
		return("Prenotazione effettuata con successo per il:\t" + this.data + "\n");
	}
	
	/**
	 * @return ritorna l'oggetto che contiene le previsioni meteo relative alla prenotazione effettuata
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
	 * @param stato del tavolo
	 */
	public void setStato(boolean stato) {
		this.stato = stato;
		
	}

	/**
	 * @return prenotazione
	 */
	public Prenotazioni getPrenotazione() {
		return prenotazione;
	}

	/**
	 * @param prenotazione da settare
	 */
	public void setPrenotazione(Prenotazioni prenotazione) {
		this.prenotazione = prenotazione;
	}
}
