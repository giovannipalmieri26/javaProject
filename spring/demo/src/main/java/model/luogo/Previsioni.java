package model.luogo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Instant;

/**
 * @author Giovanni
 * @author Giorgio
 * Classe che consente di gestire le condizioni meteorologiche relative al giorno desiderato
 *
 */
public class Previsioni {
		
		 /**
		 * String nome della città
		 */
		private String nome;
		 
		 /**
		 * Oggetto data delle previsioni che si vuole visionare
		 */
		private LocalDate data;
		 
		 /**
		 * String previsioni della città inserita
		 */
		private String principale;
		 
		 /**
		 * double temperatura della città in kelvin
		 */
		protected double temperatura;
		 
		  
		 /**
		 * @return ritorna il nome della città
		 */
		public String getNome() {
			 return this.nome;
		 }
		 
		 /**
		 * @return ritorna la stringa con le condizioni meteo
		 */
		public String getPrincipale() {
			 return this.principale;
		 }
		 
		 /**
		  * Imposta la temperatura
		 * @param temperatura temperatura da inserire
		 */
		public void setTemperatura (double temperatura) {
			 this.temperatura = temperatura ;
		 }
		 
		 /**
		 * @return float ritorna la temperatura
		 */
		public float getValore() {
				return (float)this.temperatura;
			}
		
		 /**
		 * @return ritorna la data della previsione
		 */
		public LocalDate getData() {
			 return this.data;
		 }
		 
		 /**
		  * Imposta il nome della città 
		 * @param nome il nome da inserire
		 */
		public void setNome ( String nome ) {
			 this.nome = nome;
		 } 
		 
		 /**
		  * Imposta la data della previsione metereologica che si vuole visionare
		  * Converte il long in una data
		 * @param ora long della data in UnixEpoch
		 */
		public void setData (long ora) {
			this.data = Instant.ofEpochSecond(ora).atZone(ZoneId.systemDefault()).toLocalDate();
		 }
		
		/**
		 * Imposta la condizione meteorologica
		 * @param principale stringa che contiene le condizioni meteo
		 */
		public void setCondizioni( String principale) { 
			switch(principale) {
			case "Clear" : 
				 this.principale = ("Bel tempo"); 
				 break;
			case "Clouds" : 
				this.principale = ("E' un pò nuvoloso");
				 break;
			case "Drizzle" : 
				this.principale = ("Sta piovendo un pò"); 
				 break;
			case "Rain" : 
			case "Thunderstorm" : 
			case "Snow" : 
				this.principale = ("Brutto tempo!"); 
				 break;
			case "Previsioni non disponibili":
				this.principale = ("Previsioni non disponibili");
				break;
			default : 
				this.principale = ("Condizioni Particolari"); break;
				
			}
		}
		/**
		* Ritorna un bool che rappresenta le previsioni: true se è bel tempo o 
		* le previsioni non sono disponibili, false (brutto tempo)
		* @return true(Non piove o le previsioni non sono disponibili.) false (brutto tempo)
		*/
		public boolean getCondizioni() {
			switch (this.principale) {
			case "Bel tempo" : 
				return true;
			case "Ci sono un po' di nuvole" : 
				return true;
			case "Sta piovigginando" : 
					  return false;
			case "Previsioni non disponibili":
				return true;		
			default :
				return false;
			}
		}
			
		/**
		 *Genera una stringa con le previsioni ottenute
		 */
		public String toString () {
			return ( "Nome città : " + this.nome + "\ndata: " + this.data + "\ntemperatura: " +  this.temperatura
				 + " °C\ncondizioni meteo: " + this.principale );	 
		}
}
