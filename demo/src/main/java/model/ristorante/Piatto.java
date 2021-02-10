package model.ristorante;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * 
 * Classe che rappresenta un piatto
 * 
 */

public class Piatto {

	/**
	 * Nome del piatto
	 */
	private String nome;
	/**
	 * Prezzo del piatto
	 */
	private Prezzo prezzo;
	
	
	/**
	 * Costruttore della classe che genera l'oggetto partendo dalla descrizione e dal prezzo
	 * @param nome nome del piatto
	 * @param prezzo prezzo del piatto
	 */
	public  Piatto(String nome , float prezzo) {
		this.nome = nome;
		this.prezzo = new Prezzo(prezzo);
	}
	
	/**
	 *@return float ritorna il prezzo del piatto
	 */
	public float getValore() {
		return this.prezzo.getPrezzo();
	}
	
	/**
	 * @return string ritorna il nome del piatto
	 */
	public String getDescrizione() {
		return this.nome;
	}
	
	/**
	 *Trasforma il piatto in una stringa
	 */
	public String toString() {
		return (  this.nome + " " + this.prezzo.getPrezzo() + "euro" );
	}
}


