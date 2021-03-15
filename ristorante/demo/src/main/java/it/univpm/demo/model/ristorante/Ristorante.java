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
	 * Lista delle informazioni
	 */
	public Lista<Previsioni> listaStats = new Statistiche();
	
	/**
	 * Restituisce la lista delle prenotazioni
	 * @return lista delle prenotazioni
	 */
	public ListaPrenotazioni<Prenotazioni> getListaPrenotazioni() {
		return this.listaPrenotazioni;
	}
	
	/**
	 * Setta la lista delle prenotazioni
	 * @param listaPrenotazioni delle prenotazioni
	 * @return ListaPrenotazioni lista delle prenotazioni
	 */
	public ListaPrenotazioni<Prenotazioni> setListaPrenotazioni(ListaPrenotazioni<Prenotazioni> listaPrenotazioni) {
		return this.listaPrenotazioni;
	}
	
	/**
	 * Restituisce il database
	 * @return database
	 */
	public Database getDatabase() {
		return this.database;
	}
	
	/**
	 * Setta il database
	 * @param database che si vuole settare
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}
	
	/**
	 * Restituisce un stringa con le informazioni relative al Ristorante
	 *@return String delle informazioni relative al Ristorante
	 */
	public String toString() {
		 return("Account registrati:\n\n" + database.toString() + "\n" + "Prenotazioni effettuate:\n\n" +
				 listaPrenotazioni.toString() + "\n" + "Previsioni:\n\n" + listaStats.toString());
	}
}
