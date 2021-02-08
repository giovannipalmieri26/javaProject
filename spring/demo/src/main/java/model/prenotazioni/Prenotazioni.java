package model.prenotazioni;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * 
 * Classe che gestisce le prenotazioni
 *
 */
public class Prenotazioni {
	
	/**
	 * Nome di chi prenota
	 */
	private String nome;
	
	/**
	 * Costruttore della classe
	 * @param nome  Nome di chi prenota
	 */
	public Prenotazioni (String nome) {
		this.nome = nome;			
	}
	
	/**
	 * @return Restituisce il nome di chi ha prenotato
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 *Genera una stringa a partire dalla prenotazione
	 */
	public String toString() {
		return (" Prenotazione effettuata al seguente nome:" + this.nome);
	}

}