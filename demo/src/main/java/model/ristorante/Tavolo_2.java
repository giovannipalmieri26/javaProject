package model.ristorante;

import model.interfacce.Oggetto;
import model.prenotazioni.Prenotazioni;

/*
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * 
 * Classe che rappresenta i tavoli all'interno di un ristorante
 */	
public class Tavolo_2 implements Oggetto{

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
	private static int ultimaPosizione = 1;
	/*
	 * Oggetto che rappresenta la prenotazione del tavolo
	 */
	public Prenotazioni prenotazione;

	/*
	 * Costruttore che tiene il conto delle posizioni
	 */
	public Tavolo_2() {
		this.posizione = ultimaPosizione;
		ultimaPosizione++;
	}
	
	/*
	 * Metodo che permette di inserire una nuova prenotazione
	 * @param nome nome della persona che ha effettuato la prenotazione
	 */
	public  void aggiungiPrenotazione(String nome) {
		this.prenotazione = new Prenotazioni(nome);
	}
	
	/*
	 * @return restituisce la posizione del tavolo
	 */
	public int getPosizione() {
		return this.posizione;
		}
	/*
	 * @return restituisce lo stato del tavolo
	 */
	public boolean getStato() {
		return stato;
		}

	/*
	 * Reimposta lo stato dell'ombrellone
	 * @param libero rappresenta il nuovo stato del tavolo
	 */
	public void setStato(boolean libero) {
		this.stato = libero;
		if(stato) this.prenotazione = null;
		}

	/*
	 * @return restituisce la posizione del tavolo in stringa
	 */
	public String toString() {
		if(this.stato) return("\t[ "+ this.posizione +" ]");
		else return ("\t[ Il tavolo "+ this.posizione + "è già stato prenotato ]");
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return null;
	}
}
