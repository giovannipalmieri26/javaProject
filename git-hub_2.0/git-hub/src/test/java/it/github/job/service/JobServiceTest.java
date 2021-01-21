package it.github.job.service;

import it.github.job.model.CountryPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.naming.ConfigurationException;
import java.rmi.UnexpectedException;
import java.util.List;

class JobServiceTest {

    @Test
    public void constructionTest() throws ConfigurationException {
        JobService service = new JobService();
        Assertions.assertNotNull(service);
    }


    @Test
    public void searchByCities() throws ConfigurationException, UnexpectedException {
        // Controlla che non ci siano errori per input buoni
        JobService service = new JobService();
        final List<CountryPosition> founds = service.findByLocations("berlin", "budapest", "Kyiv", "London", "Paris");
        Assertions.assertNotNull(founds);
    }


    @Test
    public void errorNoCitiesINput() {
        // controlla errori nel caso non vengano inseriti argomenti
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            JobService service = new JobService();
            service.findByLocations();
        });
    }

    @Test
    public void errorTooManyCities() {
        // controlla errori nel caso vengano inseriti troppi argomenti
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            JobService service = new JobService();
            service.findByLocations("berlin", "budapest", "Kyiv", "London", "Paris", "berlin", "budapest", "Kyiv", "London", "Paris", "berlin", "budapest", "Kyiv", "London", "Paris");
        });
    }


    @Test
    public void findByCoords() throws ConfigurationException, UnexpectedException {
        // Controlla che non ci siano errori per le coordinate inserite
        JobService service = new JobService();
        final CountryPosition founds = service.findByCoords(33.23, 43.22);
        Assertions.assertNotNull(founds);
    }

    @Test
    public void filterByMostRecent() throws ConfigurationException, UnexpectedException {
        // Controlla che non ci siano errori per i dati inseriti
        JobService service = new JobService();
        final List<CountryPosition> founds = service.filterBestResults(3, "berlin", "budapest", "Kyiv", "London", "Paris" );
        Assertions.assertNotNull(founds);
    }

}
