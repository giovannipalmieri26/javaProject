package it.univpm.demo.model.liste;

import it.univpm.demo.model.prenotazioni.Prenotazioni;

public class ListaPrenotazioni<T extends Prenotazioni> extends Lista<T>{
	
	public String toString() {
		String ritorno = "";
		for(T Elemento : this.lista) ritorno = (ritorno + "(" + this.lista.indexOf(Elemento) 
			+ ")" + "\n" + Elemento.toString2() + "\n\n");
		return ritorno;
	}
}
