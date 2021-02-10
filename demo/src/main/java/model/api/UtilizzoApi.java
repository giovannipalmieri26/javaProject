package model.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDate;
import java.lang.NullPointerException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import model.liste.Lista;
import model.luogo.Previsioni;
import java.lang.ClassCastException;

/**
 * @author Giovanni
 * @author Mattia
 * @author Giorgio
 * 
 * Classe che permette la lettura dell'Api openweather
 * 
 *
 */

public class UtilizzoApi {
	/**
	 * URL dell'Api openweather
	 */
	private String urlMeteo = "http://model.api.openweathermap.org/data/2.5/forecast?id=6540261&appid=fab715b1276e37b8c17a87274e509451";  
	/**
	 * Numero dei giorni di cui l'Api permette di visionare le previsioni 
	 */
	private int giorniDaVedere = 5;
	/**
	 * Rilevazioni effettate in un singolo giorno
	 */
	private int scansioniGiornaliere = 8;
	/**
	 * Scansioni totali effettuate
	 */
	private int scansioniTotali = this.giorniDaVedere * this.scansioniGiornaliere;
	
	
	/**
	 * Valorizza una lista aggiungendo le previsioni che si desiderano
	 * @return Lista previsioni 
	 */
	public Lista<Previsioni> valorizzaListaPrevisioni() {
		 
		JSONObject obj = this.leggiApi(this.urlMeteo) ;
		
		Lista<Previsioni> lista = new Lista<Previsioni>();
			
		JSONArray list = (JSONArray) obj.get("list");
			
		for(int i = 0; i < scansioniTotali ; i+=scansioniGiornaliere) {
				
		Previsioni previsione = new Previsioni();
				
		JSONObject oggettoLista = (JSONObject) list.get(i);	
		
		try {
			
		previsione.setData((long) oggettoLista.get("dt"));
			
		JSONObject city = (JSONObject) obj.get("city");
			
		previsione.setNome((String) city.get("name"));
				
		JSONObject main = (JSONObject) oggettoLista.get("main");
			
		previsione.setTemperatura((double) main.get("temp")); 
		}
		catch(ClassCastException e) {
			return null;	
		}
			
		JSONArray weather = (JSONArray) oggettoLista.get("weather");
			
		JSONObject obj2 = (JSONObject) weather.get(0) ;
			
		previsione.setCondizioni((String) obj2.get("main"));
			
		lista.aggiungi(previsione);
			}
		return lista;
	}
		
	
	/**
	 * Permette di leggere l'Api e restituire il JsonObject corrispondente
	 * @param URL dell'Api da leggere
	 * @return JsonObject contenente le informazioni richieste
	 */
	private JSONObject leggiApi(String url) { 
	try {
		
		URLConnection openConnection = new URL(url).openConnection();
		InputStream in = openConnection.getInputStream();
		String data = "";
		String line = "";
		 try {
		   InputStreamReader inR = new InputStreamReader( in );
		   BufferedReader buf = new BufferedReader( inR );
		  
		   while ( ( line = buf.readLine() ) != null ) {
			   data+= line;
		   }
		 } finally {
		   in.close();
		 }
		 
		
		JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
		return obj;
		
	} catch (IOException | ParseException e) {
		e.printStackTrace();
		return null;
		
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	}
	
	/**
	 * Valorizza l'oggetto previsione
	 * @param data Data delle previsioni che si vuole cercare
	 * @return restituisce la previsione corrispondente
	 */
	public Previsioni valorizzaPrevisione(LocalDate data) {
			JSONObject obj = this.leggiApi(this.urlMeteo);
			Previsioni previsione = new Previsioni();
			if(obj == null) {
				previsione.setCondizioni("Previsioni non disponibili");
				return previsione;
			}
			JSONArray list = (JSONArray) obj.get("list");
			for(int i = 0; i < scansioniTotali ; i+=scansioniGiornaliere) {	
					JSONObject oggettoLista = (JSONObject) list.get(i);	
					try {
						long epochDay = (long) oggettoLista.get("dt");
						LocalDate controlla = Instant.ofEpochSecond(epochDay).atZone(ZoneId.systemDefault()).toLocalDate();
						if(data.getDayOfYear() == controlla.getDayOfYear()) {
							previsione.setData(epochDay);
							JSONObject city = (JSONObject) obj.get("city");
							previsione.setNome((String) city.get("name"));
							JSONObject main = (JSONObject) oggettoLista.get("main");
							previsione.setTemperatura((double) main.get("temp")); 
							JSONArray weather = (JSONArray) oggettoLista.get("weather");
							JSONObject obj2 = (JSONObject) weather.get(0) ;
							previsione.setCondizioni((String) obj2.get("main"));
							return previsione;
						}
					}
					catch(ClassCastException | NullPointerException e) {
						previsione.setCondizioni("Previsioni non disponibili");
						return previsione;
					}
			}
			previsione.setCondizioni("Previsioni non disponibili");
			return previsione;		
	}
}