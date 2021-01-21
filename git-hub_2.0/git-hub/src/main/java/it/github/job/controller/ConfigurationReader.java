package it.github.job.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Classe necessaria per richiamare la configurazione da file e dunque per la lettura del file. 
 * Il modello singleton è un modello di progettazione software che limita la creazione di istanze di una 
 * classe a una "singola" istanza. 
 * */
public class ConfigurationReader {

    private static final Logger log = Logger.getLogger(ConfigurationReader.class.getName());

    //istanza
    private static ConfigurationReader instance;

    private static Properties properties;

    //costruttore privato
    private ConfigurationReader() throws IOException {
        properties = readProperties();
    }


    /**
     * Carica le properties dal file: "properties.json"
     *
     * @return Properties caricate dal file
     * @throws IOException nel caso in cui il file non venga trovato
     */
    private static Properties readProperties() throws IOException {
      // path of properties
      Path path = Paths.get("properties.json");
      final byte[] bytes = Files.readAllBytes(path);
      String data = new String(bytes);
      //        LinkedHashMap map = new Gson().fromJson(data, LinkedHashMap.class);
      final Properties properties = new Properties();
      JSONObject obj;
      try {
        obj = (JSONObject) JSONValue.parseWithException(data);
        properties.put("github_api",obj.get("github_api"));
        for (Iterator iterator = obj.entrySet().iterator(); iterator.hasNext();) {
          Map.Entry property = (Map.Entry) iterator.next();
          properties.put((String)property.getKey(), (String)property.getValue());
        }

        log.info("Properties loaded");
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return properties;
    }

    /**
     * Singleton method
     *
     * @return Instance of ConfigurationReader
     */
    public static ConfigurationReader getInstance() {
        if (instance == null) {
            try {
                instance = new ConfigurationReader();
            } catch (IOException e) {
                log.log(Level.SEVERE, "Unable to load configuration", e);
                throw new IllegalStateException("Unable to load configuration", e);
            }
        }
        return instance;
    }

    /**
     * Richiama la singola property
     *
     * @param key nome della property
     * @return i valori della property, null se non trova nulla
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Richiama la singole property come una Lista di Stringhe splittate da ","
     *
     * @param key nome della property
     * @return i valori della property come Lista, null se non trova nulla
     */
    public List<String> getPropertyAsList(String key) {
        // questo è necessario perchè asList genera immutable list
        final String property = properties.getProperty(key);
        if (property != null) {
            return new LinkedList<>(Arrays.asList(property.split(",")));
        } else return new ArrayList<>();
    }
}
