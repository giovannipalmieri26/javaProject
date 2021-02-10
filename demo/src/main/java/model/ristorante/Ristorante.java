package model.ristorante;

import model.registrazione.*;
import model.liste.*;
import model.prenotazioni.Prenotazioni;

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
	public Database database = new Database ();
	
	/**
	 * Lista dei tavoli
	 */
	public Tavolo_3 Tavolo;
	
	/**
	 * Lista dei tavoli
	 */
	public ListaTavoli listaTavoli = new ListaTavoli ();
	
	/**
	 * Lista delle prenotazioni
	 */
	public Lista<Prenotazioni> listaPrenotazioni = new Lista<Prenotazioni> ();
	
	/**
	 * Lista delle informazioni
	 */
	public Statistiche listaStats = new Statistiche();	
}
