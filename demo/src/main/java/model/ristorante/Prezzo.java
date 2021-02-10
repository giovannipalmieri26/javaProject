package model.ristorante;
/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 *
 */

public class Prezzo {
	
	/**
	 * @param prezzo prezzo di tipo float
	 */
	private float prezzo;
	
	/** Costruttore della classe Prezzo che prende in ingresso il parametro prezzo
	 */
	public Prezzo(float prezzo) {
		
		this.prezzo = prezzo;
	}
	
	/**
	 * Metodo utilizzato per settare il prezzo
	 */
	public void setPrezzo ( float prezzo ) {  
		this.prezzo = prezzo;
	}
	
	/**
	 * Metodo get che restituisce il prezzo
	 */
	public float getPrezzo () {
		
		return this.prezzo;
	}
	
	/**
	 * Metodo che restituisce il valore del prezzo trasformato in stringa
	 */
	public String toString () {
		
		return ("Prezzo :" + prezzo );
	}
}
