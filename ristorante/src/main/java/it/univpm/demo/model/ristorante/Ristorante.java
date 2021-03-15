package it.univpm.demo.model.ristorante;

import it.univpm.demo.model.liste.*;
import it.univpm.demo.model.luogo.Previsioni;
import it.univpm.demo.model.prenotazioni.Prenotazioni;
import it.univpm.demo.model.registrazione.*;

/**
 * @author Giovanni
 * @author Mattia
 * @author Giorgio
 * 
 *Classe contenente le varie liste, gli account e il database relativamente al ristorante
 */
public class Ristorante {
	
	/**
	 * Database del ristorante
	 */
	private Database database = new Database ();
	
	/**
	 * Lista delle prenotazioni
	 */
	private ListaPrenotazioni<Prenotazioni> listaPrenotazioni = new ListaPrenotazioni<Prenotazioni>();
	
	/**
	 * Chiama l'api e valorizza l'oggetto Previsioni
	 */
	public ListaPrenotazioni<Prenotazioni> getListaPrenotazioni() {
		return this.listaPrenotazioni;
	}
	
	/**
	 * Lista delle informazioni
	 */
	public Lista<Previsioni> listaStats = new Statistiche();
	
	/**
	 * Restituisce il database
	 */
	public Database getDatabase() {
		return database;
	}
	
	/**
	 * Setta il database
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}
	
	/**
	 * Restituisce un stringa con le informazioni relative al Ristorante
	 */
	public String toString() {
		 return("Account registrati:\n\n" + database.toString() + "\n" + "Prenotazioni effettuate:\n\n" +
				 listaPrenotazioni.toString() + "\n" + "Previsioni:\n\n" + listaStats.toString());
	}
}
