package it.univpm.demo.model.prenotazioni;

import it.univpm.demo.model.ristorante.Piatto;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * 
 * Classe che gestisce le prenotazioni
 *
 */
public class Prenotazioni{
	
	/**
	 * Nome di chi prenota
	 */
	public int numero = 0; 
	
	/**
	 * Nome di chi prenota
	 */
	public String nome;
	

	/**
	 * Antipasto
	 */
	private Piatto antipasto;
	

	/**
	 * Primo
	 */
	private Piatto primo;
	

	/**
	 * Secondo
	 */
	private Piatto secondo;
	

	/**
	 * Dolce
	 */
	private Piatto dolce;
	

	/**
	 * Conto della prenotazione
	 */
	private double conto;
	

	
	/**
	 * Costruttore della classe
	 * @param nome  Nome di chi prenota
	 * @param antipasto che si desidera ordinare
	 * @param primo che si desidera ordinare
	 * @param secondo che si desidera ordinare
	 * @param dolce che si desidera ordinare
	 */
	public Prenotazioni (String nome, Piatto antipasto, Piatto primo, Piatto secondo, Piatto dolce) {
		this.nome = nome;
		this.antipasto = antipasto;
		this.primo = primo;
		this.secondo = secondo;
		this.dolce = dolce;
	}
	
	/**
	 *Genera una stringa a partire dalla prenotazione
	 */
	public String toString() {
		if(getAntipasto() != null) return ("L'ordine di " + this.nome + " è il seguente:\n\n" + 
				"ANTIPASTO:	" + getAntipasto().toString() + "\n" + "PRIMO: " + getPrimo().toString() + "\n"
				+ "SECONDO: " + getSecondo().toString() + "\n" + "DOLCE: " + getDolce().toString());
		return ("L'ordine di " + this.nome + " è il seguente:\n\n" + "\n" 
				+ "PRIMO: " + getPrimo().toString() + "\n" + "SECONDO: " + getSecondo().toString() 
				+ "\n" + "DOLCE: " + getDolce().toString());
	}
	
	public String toString2() {
		return ("L'ordine di " + this.nome + " è il seguente:\n\n" + 
				"ANTIPASTO:	" + getAntipasto().getNome() + "\n" + "PRIMO: " + getPrimo().getNome() + "\n"
				+ "SECONDO: " + getSecondo().getNome() + "\n" + "DOLCE: " + getDolce().getNome());
	}

	/**
	 * @return the antipasto
	 */
	public Piatto getAntipasto() {
		return antipasto;
	}

	/**
	 * @param antipasto the antipasto to set
	 */
	public void setAntipasto(Piatto antipasto) {
		this.antipasto = antipasto;
	}

	/**
	 * @return the primo
	 */
	public Piatto getPrimo() {
		return primo;
	}

	/**
	 * @param primo the primo to set
	 */
	public void setPrimo(Piatto primo) {
		this.primo = primo;
	}

	/**
	 * @return the secondo
	 */
	public Piatto getSecondo() {
		return secondo;
	}

	/**
	 * @param secondo the secondo to set
	 */
	public void setSecondo(Piatto secondo) {
		this.secondo = secondo;
	}

	/**
	 * @return the dolce
	 */
	public Piatto getDolce() {
		return dolce;
	}

	/**
	 * @param dolce che si desidera ordinare
	 */
	public void setDolce(Piatto dolce) {
		this.dolce = dolce;
	}

	/**
	 * @return conto della prenotazione effettuata
	 */
	public double getConto() {
		conto = this.antipasto.getPrezzo() + this.primo.getPrezzo() +
				this.secondo.getPrezzo() + this.dolce.getPrezzo();
		return conto;
	}
}
	
	
	
	
	