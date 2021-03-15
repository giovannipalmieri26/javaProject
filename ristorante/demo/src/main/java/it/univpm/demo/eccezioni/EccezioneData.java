package it.univpm.demo.eccezioni;

public class EccezioneData extends Exception{
	private static final long serialVersionUID = 1L;
	public EccezioneData() {
		super("La data non è stata inserita correttamente. Riprovare");
	}
}