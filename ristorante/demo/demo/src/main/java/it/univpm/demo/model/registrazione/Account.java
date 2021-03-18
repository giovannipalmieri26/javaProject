package it.univpm.demo.model.registrazione;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * Classe che permette di creare un account
 *
 */
public class Account  {
	private String username;
	private String password;
	
	/**
	 * Costruttore dell'account
	 * @param username nome 
	 * @param password password
	 */
	public  Account( String username ,String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Controlla la password
	 * @param password da verificare
	 * @return true se è uguale, altrimenti false
	 */
	public boolean controllaPassword (String password) {
		if(password.equalsIgnoreCase(this.password)) return true;
		else return false;
	}
	
	/**
	 * Permette di resettare la password
	 * @param vecchiaPassword per la verifica
	 * @param nuovaPassword nuova password da inserire
	 * @return se la password è stata modificata restituisce true, altrimenti false
	 */
	public boolean modificaPass(String vecchiaPassword, String nuovaPassword) {
		if (this.controllaPassword(vecchiaPassword)) {
			this.password = nuovaPassword;
			return true;
		}
		else return false;
		}
	
	/**
	 * Cambia lo username
	 * @param password dell'account
	 * @param nuovoUsername nuovo username da inserire
	 * @return true se lo username è stato modificato, altrimenti false
	 */
	public boolean modificaUsername(String password, String nuovoUsername) {
		if (this.controllaPassword(password)) {
			this.username = nuovoUsername;
			return true;
			}
		else return false;
		}
	
	/**
	 * Restituisce lo username
	 * @return String con lo username
	 */
	public String getUsername() {
		return this.username;
		}
	
	/**
	 * Restituisce la password
	 * @return String con la password
	 */
	public String getPassword() {
		return this.password;
		}
	
	/** 
	 * Restituisce il toString dello username
	 * @return ritorna in stringa la classe 
	 */
	public String toString() {
		return(	 "User: " + username + "\n");
	}	
}

