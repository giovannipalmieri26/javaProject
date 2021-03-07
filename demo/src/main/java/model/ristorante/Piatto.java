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
	private double prezzo;
	
	
	/**
	 * Costruttore della classe che genera l'oggetto partendo dalla descrizione e dal prezzo
	 * @param nome nome del piatto
	 * @param prezzo prezzo del piatto
	 */
	public  Piatto(String nome , double prezzo) {
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	/**
	 *@return double ritorna il prezzo del piatto
	 */
	public double getValore() {
		return this.prezzo;
	}
	
	/**
	 *@return double setta il prezzo del piatto
	 */
	public void setValore(double valore) {
		this.prezzo = valore;
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
		return (  this.nome + " " + getValore() + " euro" );
	}
}


