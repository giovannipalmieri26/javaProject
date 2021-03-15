package it.univpm.demo.model.liste;

import java.util.Vector;

import it.univpm.demo.model.ristorante.Ristorante;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * 
 *Classe utilizzata per la creazione di una lista di oggetti generici
 * @param <T> generico oggetto della lista
 */
public class Lista<T> {
	
	/**
	 * Vector che contiene gli oggetti inseriti
	 */
	protected Vector<T> lista = new Vector <T>();
	
	public Ristorante ristorante;
	
	/**
	 * 
	 * @param oggetto oggetto da inserire nel vector
	 */
	public void aggiungi(T oggetto) {
		this.lista.add(oggetto);
	}
	
	/**
	 *Genera una stringa a partire dalla lista
	 */
	public String toString() {
		String ritorno = "";
		for(T Elemento : this.lista) ritorno = (ritorno + Elemento.toString() + "\n\n");
		return ritorno;
	}
	
	/**
	 * Elimina l'oggetto della lista in una posizione
	 * @param posizione posizione dell'oggetto da eliminare
	 */
	public void elimina(int posizione) {
		this.lista.remove(posizione);
}
	
	/**
	 * Metodo che ritorna il Vector di oggetti
	 * @return vector di generici oggetti T
	 */
	public Vector <T> getLista(){
		return this.lista;
	}
}