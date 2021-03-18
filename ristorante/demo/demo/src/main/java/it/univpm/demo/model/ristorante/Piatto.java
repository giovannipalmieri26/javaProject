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
	 * Costruttore della classe che genera il piatto partendo dal nome e dal prezzo
	 * @param nome del piatto
	 * @param prezzo del piatto
	 */
	public  Piatto(String nome , double prezzo) {
		this.nome = nome;
		this.prezzo = (float) prezzo;
	}
	
	/**
	 * Restituisce il prezzo del piatto
	 *@return float prezzo del piatto
	 */
	public double getPrezzo() {
		return (int) this.prezzo;
	}
	
	/**
	 * Setta il prezo del piatto
	 *@param prezzo del piatto che si vuole settare
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * Restituisce il nome del piatto
	 * @return String contenente il nome del piatto
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restituisce il piatto sottoforma di stringa
	 * @String del piatto
	 */
	public String toString() {
		if(this.nome == "-") return (this.nome);
		return (this.nome + " " + getPrezzo() + " euro");
	}	
	
}


