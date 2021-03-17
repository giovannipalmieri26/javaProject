package it.univpm.demo.model.liste;

import it.univpm.demo.model.prenotazioni.Prenotazioni;

public class ListaPrenotazioni<T extends Prenotazioni> extends Lista<T> {
	
	/*
	 *Restituisce la stringa ch contiene la lista delle prenotazioni 
	 * @return String della lista delle prenotazioni
	 */
	public String toString() {
		String ritorno = "";
		for(T Elemento : this.lista) ritorno = (ritorno + "(" + this.lista.indexOf(Elemento) 
			+ ")" + "\n" + Elemento.toString2() + "\n\n");
		return ritorno;
	}
	
	/**
	 *Genera una lista di prenotazioni a partire da un'altra lista
	 *@param appoggio listaPrenotazioni da inserire in input
	 *@return ListaPrenotazioni lista di prenotazioni
	 */
	public ListaPrenotazioni<Prenotazioni> setListaPrenotazioni(ListaPrenotazioni<Prenotazioni> appoggio) {
		for(T Elemento : this.lista) appoggio.aggiungi(Elemento);
		return appoggio;
	}
}