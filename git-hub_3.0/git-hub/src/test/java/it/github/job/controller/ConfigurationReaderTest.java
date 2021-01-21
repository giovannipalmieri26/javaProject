package it.github.job.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Test class per ConfigurationReader.class
class ConfigurationReaderTest {


    @Test
    public void instanceTest() {
        // test singleton by doing equal on object
        final ConfigurationReader instance = ConfigurationReader.getInstance();
        Assertions.assertNotNull(instance);
        final ConfigurationReader instance2 = ConfigurationReader.getInstance();
        Assertions.assertEquals(instance, instance2);
    }

    @Test
    public void configurationReaderConstruction() {
        // test construction work
        final ConfigurationReader instance = ConfigurationReader.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    public void propertiesLoaded() {
        // test properties caricate e valore trovato
        final ConfigurationReader instance = ConfigurationReader.getInstance();
        Assertions.assertNotNull(instance);
        final String found = instance.getProperty("github_api");
        Assertions.assertNotNull(found);
    }

    @Test
    public void propertyNotFound() {
        // test property caricata e property non trovata
        final ConfigurationReader instance = ConfigurationReader.getInstance();
        Assertions.assertNotNull(instance);
        final String notFound = instance.getProperty("XXXX");
        Assertions.assertNull(notFound);
    }
}
