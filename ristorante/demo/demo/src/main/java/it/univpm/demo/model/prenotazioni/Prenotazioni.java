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
	 * Numero della prenotazione
	 */
	public int numero = 0; 
	
	/**
	 * Nome di chi effettua la prenotazione
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
	 * Boolean relativo allo stato della prenotazione: true se il tavolo è stato prenotato all'esterno, false se è stato prenotato all'interno 
	 */
	private boolean ordine;
	
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
	 * @param ordine true se la prenotazione del tavolo è stata effettuata all'esterno altrimenti false
	 */
	public Prenotazioni (String nome, Piatto antipasto, Piatto primo, Piatto secondo, Piatto dolce, boolean ordine) {
		this.nome = nome;
		this.antipasto = antipasto;
		this.primo = primo;
		this.secondo = secondo;
		this.dolce = dolce;
		this.setOrdine(ordine);
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
	
	/**
	 *Genera una stringa a partire dalla prenotazione che non contiene il costo dei singoli piatti ordinati
	 */
	public String toString2() {
		return ("L'ordine di " + this.nome + " è il seguente:\n\n" + 
				"ANTIPASTO:	" + getAntipasto().getNome() + "\n" + "PRIMO: " + getPrimo().getNome() + "\n"
				+ "SECONDO: " + getSecondo().getNome() + "\n" + "DOLCE: " + getDolce().getNome());
	}

	/**
	 * Restituisce l'antipasto ordinato
	 * @return antipasto
	 */
	public Piatto getAntipasto() {
		return antipasto;
	}

	/**
	 * Setta l'antipasto ordinato
	 * @param antipasto da settare
	 */
	public void setAntipasto(Piatto antipasto) {
		this.antipasto = antipasto;
	}

	/**
	 * Restituisce il primo ordinato
	 * @return primo
	 */
	public Piatto getPrimo() {
		return primo;
	}

	/**
	 * Setta il primo ordinato
	 * @param primo the primo to set
	 */
	public void setPrimo(Piatto primo) {
		this.primo = primo;
	}

	/**
	 * Restituisce il secondo ordinato
	 * @return the secondo
	 */
	public Piatto getSecondo() {
		return secondo;
	}

	/**
	 * Setta il secondo ordinato
	 * @param secondo the secondo to set
	 */
	public void setSecondo(Piatto secondo) {
		this.secondo = secondo;
	}

	/**
	 * Restituisce il dolce ordinato
	 * @return the dolce
	 */
	public Piatto getDolce() {
		return dolce;
	}

	/**
	 * Setta il dolce ordinato
	 * @param dolce che si desidera ordinare
	 */
	public void setDolce(Piatto dolce) {
		this.dolce = dolce;
	}

	/**
	 * Restituisce il conto della prenotazione
	 * @return conto della prenotazione effettuata
	 */
	public double getConto() {
		conto = this.antipasto.getPrezzo() + this.primo.getPrezzo() +
				this.secondo.getPrezzo() + this.dolce.getPrezzo();
		return conto;
	}

	/**
	 * Restituisce lo stato dell'ordine
	 * @return ordine
	 */
	public boolean getOrdine() {
		return ordine;
	}

	/**
	 * Setta lo stato dell'ordine
	 * @param ordine da settare
	 */
	public void setOrdine(boolean ordine) {
		this.ordine = ordine;
	}
}
	
	
	
	
	
