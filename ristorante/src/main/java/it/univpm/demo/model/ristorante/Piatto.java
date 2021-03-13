package it.univpm.demo.model.ristorante;

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
	 * @param costo prezzo del piatto
	 */
	public  Piatto(String nome , double costo) {
		this.nome = nome;
		this.prezzo = (float) costo;
	}
	
	/**
	 *@return float ritorna il prezzo del piatto
	 */
	public double getValore() {
		return this.prezzo;
	}
	
	/**
	 *@return double setta il prezzo del piatto
	 */
	public void setValore(double prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * @return string ritorna il nome del piatto
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 *Trasforma il piatto in una stringa
	 */
	public String toString() {
		if(this.nome == "-") return (this.nome);
		return (this.nome + " " + getValore() + " euro");
	}
}


