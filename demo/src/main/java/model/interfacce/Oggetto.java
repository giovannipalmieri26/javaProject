package model.interfacce;

public interface Oggetto  {
	public boolean getStato();
	
	public void setStato(boolean stato);
	
	public String getTipo();
	
	public  void aggiungiPrenotazione(String nome);
	}