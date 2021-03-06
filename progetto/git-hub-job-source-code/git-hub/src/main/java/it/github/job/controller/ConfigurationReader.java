package it.github.job.controller;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class needed for retrieve configuration from file. Singleton cause we need to keep configuration same for all app
 */
public class ConfigurationReader {

    private static final Logger log = Logger.getLogger(ConfigurationReader.class.getName());

    // FIXME istanza
    private static ConfigurationReader instance;

    private static Properties properties;

    // FIXmE costruttore privato
    private ConfigurationReader() throws IOException {
        properties = readProperties();
    }


    /**
     * Load properties from file: "properties.json"
     *
     * @return Properties loaded from file
     * @throws IOException case of file not found
     */
    private static Properties readProperties() throws IOException {
        // path of properties
        Path path = Paths.get("properties.json");
        final byte[] bytes = Files.readAllBytes(path);
        String data = new String(bytes);
        LinkedHashMap map = new Gson().fromJson(data, LinkedHashMap.class);
        final Properties properties = new Properties();
        properties.putAll(map);
        log.info("Properties loaded");
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
     * Retrieve single property
     *
     * @param key name of property
     * @return property value, null if not found
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Retrieve single property as List of String split by ","
     *
     * @param key name of property
     * @return property values as list, null if not found
     */
    public List<String> getPropertyAsList(String key) {
        // this is necessary cause asList generate immutable list
        final String property = properties.getProperty(key);
        if (property != null) {
            return new LinkedList<>(Arrays.asList(property.split(",")));
        } else return new ArrayList<>();
    }
}
