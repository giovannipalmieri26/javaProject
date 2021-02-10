package model.liste;

import model.interfacce.*;;

/**
 * @author Giovanni
 * @author Giorgio
 * @author Mattia
 * Classe che si occupa di compiere operazioni utilizzando gli 
 * oggetti delle classi che estendono l'intefaccia Oggetto 
 *
 * @param <T> generico oggetto Oggetto
 */
public class ListaOggetti<T extends Oggetto>  extends Lista<T> {
	
	/**
	 * Consente di ordinare un certo oggetto tramite il nome dell'oggetto (richiesta) 
	 * e il nome di chi fa la prenotazione (nome)
	 * Scorre la lista di oggetti ed effettua due verifiche: 1) se è presente un oggetto con lo stesso nome della 
	 * richiesta. 2) Verifica se quell'oggetto è disponibile.
	 * In caso di successo, effettua la prenotazione dell'oggetto
	 * @param richiesta nome dell'oggetto
	 * @param nome nome di chi effettua la prenotazione
	 * @return ritorna true in caso di successo, altrimenti false
	 */
	public boolean prenota (String richiesta , String nome) {
		for(T elemento : super.lista) {
			
			if(richiesta.equalsIgnoreCase(elemento.getTipo()) && elemento.getStato()) {
				elemento.setStato(false);
				elemento.aggiungiPrenotazione(nome);
				return true;
			}	
	}
	return false;
	}
	
}
