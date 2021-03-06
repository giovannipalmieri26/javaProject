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
        // Check no error for good input
        JobService service = new JobService();
        final List<CountryPosition> founds = service.findByLocations("berlin", "budapest", "Kyiv", "London", "Paris");
        Assertions.assertNotNull(founds);
    }


    @Test
    public void errorNoCitiesINput() {
        // check error case no arguments
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            JobService service = new JobService();
            service.findByLocations();
        });
    }

    @Test
    public void errorTooManyCities() {
        // check error case too many arguments
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            JobService service = new JobService();
            service.findByLocations("berlin", "budapest", "Kyiv", "London", "Paris", "berlin", "budapest", "Kyiv", "London", "Paris", "berlin", "budapest", "Kyiv", "London", "Paris");
        });
    }


    @Test
    public void findByCoords() throws ConfigurationException, UnexpectedException {
        // Check no error for coords input
        JobService service = new JobService();
        final CountryPosition founds = service.findByCoords(33.23, 43.22);
        Assertions.assertNotNull(founds);
    }

    @Test
    public void filterByMostRecent() throws ConfigurationException, UnexpectedException {
        // Check no error for coords input
        JobService service = new JobService();
        final List<CountryPosition> founds = service.filterBestResults(3, "berlin", "budapest", "Kyiv", "London", "Paris" );
        Assertions.assertNotNull(founds);
    }

}
