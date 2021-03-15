package it.univpm.demo.model.registrazione;

import java.util.Vector;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * Classe che rappresenta il database degli account creati
 */
public class Database {
	
	/**
	 * Vector di account creati
	 */
	private Vector<Account> lista = new  Vector<Account>();
		
	/**
	 * Metodo che consente di effettuare il login tramite username e password
	 * Verifica che lo username sia presente nel database e poi controlla che la password inserita corrisponda 
	 * allo username indicato
	 * @param username String dello username dell'utente
	 * @param password String della password dell'utente
	 * @return true se lo username e la password sono giustamente accoppiati, altrimenti false
	 */
	public boolean login (String username , String password) {
		if (this.controlla(username)) {
			if(this.controlla(username, password)){ 
				return true;
				}
			else return false;
		}
		else return false;
		
}
	
	/**
	 * Consente di registrare un account ed aggiungerlo alla lista degli account 
	 * se lo username non è già stato utilizzato da un altro utente
	 * @param username String con lo username dell'account che si vuole registrare
	 * @param password String  con la password dell'account che si vuole registrare
	 * @return boolean true se l'account viene creato con successo, altrimenti false
	 */
	public boolean creaAccount (String username , String password) {
		if (!this.controlla(username)){
			Account nuovo = new Account(username , password);
				this.lista.add(nuovo);
				return true;
				}
		else  return false;
		
		}
	
	/**
	 *Genera la lista degli account sottoforma di Stringa
	 */
	public String toString() {
		String ritorno = "";
		for(Account Elemento : this.lista) ritorno = (ritorno + Elemento.toString() + "\n");
		return ritorno;
	}
	
	/**
	 * Controlla se esiste un account con lo username inserito
	 * @param username Stringa da controllare
	 * @return  boolean true se lo username si trova nella lista, altrimenti false 
	 */
	private boolean controlla(String username) {
		
		for(Account Utente : this.lista)  {
			if(username.equalsIgnoreCase(Utente.getUsername())) {
				return true;
			}
		}
		return false;
			}
	
	/**
	 * Ritorna la lista degli account
	 * @return Vector vettore contenente gli account
	 */
	public Vector <Account> getLista(){
		return this.lista;
	}
	
	/**
	 * Verifica se la password inserita corrisponde alla password associata allo username inserito
	 * @param username String con lo username dell'account 
	 * @param password String password di cui si vuole controllare la corrispondenza
	 * @return boolean true se username e password sono associati allo stesso account 
	 */
	private boolean controlla(String username , String password) {
		
		for(Account Utente : this.lista)  
			if(username.equalsIgnoreCase(Utente.getUsername()) &&  password.equalsIgnoreCase(Utente.getPassword()) ) return true;
		return false;
			}
	
	/**
	 * Metodo che permettere di inserire un account alla lista
	 * @param account account da aggiungere alla lista
	 */
	public void	aggiungiAccount(Account account) {
		this.lista.add(account);
	}
}


