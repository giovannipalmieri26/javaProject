package model.ristorante;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Vector;
import model.prenotazioni.*;
import model.api.UtilizzoApi;
import model.interfacce.Oggetto;
import model.luogo.Previsioni;

/**
 * @author Giovanni
 * @author Giorgio 
 * @author Mattia
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
	 * int numero persone prenotate
	 */
	private int prenotati;
	
	/**
	 * Oggetto che contiene le previsioni meteo per quella giornata
	 */
	private Previsioni previsione;
	
	/**
	 * Vector che contiene  le prenotazioni per l'evento 
	 */
	private Vector <Prenotazioni> listaPrenotazioni= new Vector <Prenotazioni>();

	/*
	 * Numero che rappresenta il numero del tavolo
	 */
	private int posizione ;	
		
	/*
	 * Attributo che indica lo stato della prenotazione:
	 * - true se il tavolo è libero
	 * - false se il tavolo è occupato
	 */
	private boolean stato = true;
		
	/*
	* Attributo che inizializza l'ultima posizione del tavolo
	 */
	//private static int ultimaPosizione = 1;
		
	/*
	 * Oggetto che rappresenta la prenotazione del tavolo
	 */
	public Prenotazioni prenotazione;

	/*
	 * @return restituisce la posizione del tavolo
	 */
	public int getPosizione() {
		return this.posizione;
		}

		/*
		 * Reimposta lo stato del tavolo
		 * @param libero rappresenta il nuovo stato del tavolo
		 */
		public void setStato(boolean libero) {
			this.stato = libero;
			if(stato) this.prenotazione = null;
			}

		/*
		 * @return restituisce la posizione del tavolo in stringa
		 */
		public String toString1() {
			if(this.stato) return("\t[ "+ this.posizione +" ]");
			else return ("\t[ Il tavolo "+ this.posizione + "è già stato prenotato! ]");
		}

	/**
	 * Costruttore della classe evento
	 * @param nome nome del tavolo
	 * @param anno anno della prenotazione del tavolo
	 * @param mese  mese della prenotazione del tavolo
	 * @param giorno giorno della prenotazione del tavolo
	 */
	public Tavolo (String nome, int anno, int mese, int giorno) {
		try {
			data =  LocalDate.of(anno,mese,giorno);
		}
		catch(DateTimeException e) {
		}
		this.nome = nome;
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
	 *@return ritorna true ovvero tavolo disponibile se le condizioni meteo lo permettono
	 */
	public boolean getStato() {
		if(this.previsione.getCondizioni()) return true;
		return false;
	}
	
	/**
	 * @return ritorna il vector che contiene le prenotazioni
	 */
	public Vector <Prenotazioni> getListaPrenotazioni(){
		return this.listaPrenotazioni;
	}
	
	/**
	 *Aggiunge una prenotazione prendendo come parametro il nome
	 */
	public void aggiungiPrenotazione(String nome) {
		Prenotazioni prenotazione = new Prenotazioni(nome);
		this.listaPrenotazioni.add(prenotazione);
	}
	
	/**
	 *Trasforma il tavolo in string
	 */
	public String toString () {
		return("\nTavolo: \t" + nome +
							"\nPrenotazione effettuata per il: \t\t" + this.data +
							"\nPosti prenotati: \t\t\t" + this.prenotati + '\n' );
		
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
}

