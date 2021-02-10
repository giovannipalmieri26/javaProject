package model.ristorante;

import model.interfacce.*;
import model.prenotazioni.*;

/**
 * @author  Giovanni
 * @author Giorgio
 * @author Mattia
 *
 */

public class Tavolo_3 implements Oggetto {
	
	/**
	 * int numeroposti numero delle persone presenti al tavolo
	 * Prenotazioni prenotazione oggetto che specifica il nome della prenotazione effettuata
	 * boolean stato serve per specificare lo stato appunto della disponibilità del tavolo
	 */
		private int numeroposti;
		private boolean stato;
		private Prenotazioni prenotazione;
		
		/**
		 * Costruttore che prende in input il numero delle persone e setta lo stato a true cioè libero
		 */
		public Tavolo_3 ( int numeropersone ) { 
			
			this.numeroposti = numeropersone;
			this.stato = true;
		}
		
		/**
		 * Metodo per restituire il numero dei posti
		 */
		public int getPosti () {
			return this.numeroposti;
		}
		
		/**
		 * Metodo che genera una stringa relativa al numero dei posti
		 */
		public String toString () {
			
			return ("Posti:" + this.numeroposti);
		}
		
		/**
		 *  Metodo per creare una nuova prenotazione
		 */
		public void aggiungiPrenotazione(String nome) {
			
			this.prenotazione = new Prenotazioni (nome);
		}
		
		/**
		 * Metodo che restituisce lo stato del tavolo, cioè libero o occupato 
		 */
		public boolean getStato () {
			return this.stato;
			
		}

		/**
		 * Metodo che setta lo stato del tavolo, cioè libero o occupato
		 */
		
		public void setStato(boolean stato) {
			this.stato = stato;	
		}
		
		/**
		 * Metodo che restituisce la prenotazione effettuata
		 */
		
		public Prenotazioni getPrenotazione () {
			return this.prenotazione;
		}

		@Override
		public String getTipo() {
			return null;
		}
		
}
