package it.univpm.demo.eccezioni;

public class EccezioneMenu extends Exception{
	private static final long serialVersionUID = 1L;
	public EccezioneMenu() {
		super("Hai inserito un cibo che non è presente nel database.");
	}
}