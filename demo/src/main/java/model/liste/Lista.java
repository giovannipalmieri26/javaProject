package model.liste;

import java.util.Vector;

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
	protected  Vector <T> lista = new Vector <T>() ;
	
	/**
	 * 
	 * @param oggetto oggetto da inserire nel vector
	 */
	public void aggiungi(T oggetto) {
		this.lista.add(oggetto);
	}
	
	
	/**
	 * Sostituisce l'oggetto nella posizione specificata con un nuovo elemento indicato
	 * @param posizione  posizione dell' oggetto da sostituire
	 * @param nuovoOggetto  il nuovo oggetto da inserire
	 */
	public void sostituisci(int posizione, T nuovoOggetto) {
		this.lista.set(posizione, nuovoOggetto);
	}

	/**
	 * Rimuove l'oggetto della lista nella posizione indicata
	 * @param posizione posizione dell'oggetto da rimuovere
	 */
	public void elimina(int posizione) {
		this.lista.remove(posizione);
}
	/**
	 *Genera una stringa a partire dalla lista
	 */
	public String toString() {
		String ritorno = "";
		for(T Elemento : this.lista) ritorno = (ritorno + Elemento.toString() + "\n");
		return ritorno;
	}
	
	/**
	 * Metodo che ritorna il Vector di oggetti
	 * @return vector di generici oggetti T
	 */
	public Vector <T> getLista(){
		return this.lista;
	}
}