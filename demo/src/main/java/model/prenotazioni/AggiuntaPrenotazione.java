package model.prenotazioni;

import model.liste.ListaOggetti;
import model.ristorante.Tavolo_3;

/*
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 */

public class AggiuntaPrenotazione extends ListaOggetti<Tavolo_3>{
	
	/**
	 * Metodo che prende in ingresso i parametri richiesti per una prenotazione del tavolo cioè
	 * richiesta(tavolo), nome e numero delle persone. Il metodo cerca, se esiste, un oggetto tavolo
	 * con il numero di posti necessari. Se è disponibile lo prenota e aggiunge un oggetto prenotazione
	 * e restituisce true. Se invece, la prenotazione non è andata a buon fine, restituisce false.
	 */
	
	public boolean prenota(String richiesta,String nome,int numPers){
		for(Tavolo_3 tavolo : super.lista) {
			
			if(richiesta.equalsIgnoreCase(tavolo.getTipo()) && tavolo.getStato()) {
				tavolo.setStato(false);
				tavolo.aggiungiPrenotazione(nome);
				return true;
			}	
	  }
	return false;
	}
	
}
