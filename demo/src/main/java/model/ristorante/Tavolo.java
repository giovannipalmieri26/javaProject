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
	 * String che contiene il nome dell'evento
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
	 * @return ritorna il vector che contiene le prenotazioni per l'evento
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
	 * @return ritorna l'oggetto che contiene le previsioni meteo per l'evento
	 */
	public Previsioni getPrevisione() {
		return this.previsione;
		
	}
	
	/**
	 * Chiama l'api e valorizza l'oggetto previsioni
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

	@Override
	public void setStato(boolean stato) {
		// TODO Auto-generated method stub
		
	}
	
}

