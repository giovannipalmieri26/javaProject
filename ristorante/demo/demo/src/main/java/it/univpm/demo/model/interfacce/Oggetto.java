package it.univpm.demo.model.interfacce;

public interface Oggetto  {
	
	/*
	 * Restituisce lo stato dell'oggetto
	 */
	public boolean getStato();
	

	/*
	 * Setta lo stato dell'oggetto
	 */
	public void setStato(boolean stato);
	

	/*
	 * Restituisce il tipo dell'oggetto
	 */
	public String getTipo();
	
	
	/*
	 * Aggiunge il nome della prenotazione
	 */
	public  void aggiungiPrenotazione(String nome);
	}

