package it.univpm.demo.model.ristorante;

import java.time.LocalDate;

import it.univpm.demo.model.liste.Lista;
import it.univpm.demo.model.luogo.Previsioni;
import it.univpm.demo.model.api.*;

/**
 * @author Giovanni
 * @author Mattia
 * @author Giorgio
 * 
 *Classe che gestisce le statistiche delle previsioni
 */
public  class Statistiche extends Lista<Previsioni> {

	/**
	 * Previsione del giorno
	 */
	private Previsioni previsione = new Previsioni();
	/**
	 * Oggetto per utilizzare l'Api 
	 */
	private UtilizzoApi api = new UtilizzoApi();
	/**
	 *  Media della temperatura della settimana
	 */
	private  float temperaturaMedia;
	
	/**
	 * Costruttore della classe
	 */
	public Statistiche() {
		LocalDate oggi = LocalDate.now();
		this.previsione = api.valorizzaPrevisione(oggi);
		this.setListaPrevisioni(api.valorizzaPrevisioniRistorante());
		this.temperaturaMedia = this.media();
		
	}
	/**
	 * Genera la somma dei valori degli oggetti presenti in lista
	 * @return somma dei valori degli oggetti presenti in lista
	 */
	public float somma() {
		float totale = 0;
		for(Previsioni oggetto : super.getLista()) totale += oggetto.getValore();
		return totale;
	}
	
	/**
	 * Genera la media dei valori degli oggetti presenti in lista
	 *@return media dei valori degli oggetti presenti in lista
	 */
	public float media() {	
		return this.somma() / super.getLista().size();	
	}
	
	/**
	 * Restituisce la media 
	 * @return String della media
	 */
	public String getMedia() {
		return (this.media() + "");
	}
	
	/**
	 * Restituisce una previsione
	 * @return Previsione 
	 */
	public Previsioni getPrevisione() {
		return this.previsione;
	}
	
	/**
	 * Restituisce la temperatura media
	 * @return float che rappresenta la temperatura media
	 */
	public float getTemperaturaMedia() {
		return this.temperaturaMedia;
	}
	
	/**
	 * Setta la listaPrevisioni
	 * @param listaPrevisioni lista delle previsioni
	 */
	public void setListaPrevisioni(Lista<Previsioni> listaPrevisioni) {
		this.lista = listaPrevisioni.getLista();
	}

}
