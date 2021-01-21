package it.github.job.service;

import it.github.job.controller.ConfigurationReader;
import it.github.job.controller.GitHubJobClient;
import it.github.job.model.CountryPosition;
import it.github.job.model.JobResult;
import it.github.job.model.JobSearchFilter;

import javax.naming.ConfigurationException;
import java.rmi.UnexpectedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobService {

    private static final Logger log = Logger.getLogger(JobService.class.getName());
    final String GITHUB_API = "github_api";
    final String FULL_TIME = "Full Time";
    private final GitHubJobClient client;
    private final List<String> otherLangToSearch;


    /**
     * Inizializzazione api session
     *
     * @throws ConfigurationException nel caso in cui manca la mandatory property (github_api)
     */
    public JobService() throws ConfigurationException {
        final String property = ConfigurationReader.getInstance().getProperty(GITHUB_API);
        otherLangToSearch = ConfigurationReader.getInstance().getPropertyAsList("language");
        if (property != null) {
            client = new GitHubJobClient(property);
        } else throw new ConfigurationException("Github Url missing");
    }


    /**
     * Trova Jobs Stats in base alla posizioni
     *
     * @param countries lista dei paesi ( max 10 paesi)
     * @return List of CountryPosition divisi da recentWorks DESC
     * @throws UnexpectedException - nel caso in cui ci fossero dei problemi del client
     * @see CountryPosition
     */
    public List<CountryPosition> findByLocations(String... countries) throws UnexpectedException {
        //Controlliamo che siano minori di 10
        if (countries == null || countries.length == 0 || countries.length > 10) {
            final String invalidCountryMessage = "Invalid countries input size ";
            log.log(Level.SEVERE, invalidCountryMessage);
            throw new IllegalArgumentException(invalidCountryMessage);
        }
        log.fine("Find job by location with input: " + Arrays.toString(countries));

        List<CountryPosition> results = new ArrayList<>();
        JobSearchFilter filter = new JobSearchFilter();
        //Facciamo una ricerca per ogni country e ne calcoliamo le statistiche richieste
        for (String country : countries) {
            filter.setLocation(country);
            final CountryPosition byFilter = findByFilter(filter);
            byFilter.setCountry(country);
            log.fine("Found " + byFilter.getJobFound().size() + " job for country: " + country);
            results.add(byFilter);
        }
        // ordiniamo il risultato dal numero di lavoro più recente
        results.sort(Comparator.comparingInt(CountryPosition::getRecentWorks).reversed());
        return results;
    }

    /**
     * Esegue la ricerca con i filtri e ne calcola le statistiche
     *
     * @param filter filtro per Github API
     * @return CountryPosition dei job results
     * @throws UnexpectedException - nel caso in cui ci fosse un errore del client
     * @see CountryPosition
     */
    private CountryPosition findByFilter(JobSearchFilter filter) throws UnexpectedException {
        final List<JobResult> search = new ArrayList<>();
        executeCallWithPage(filter, search, 0);
        CountryPosition countryPosition = new CountryPosition();
        countryPosition.setJobFound(search);
        if (!search.isEmpty()) {
            for (JobResult jobResult : search) {
                // statistiche
                // recente se è stato creato nell'ultimo mese
                // capiamo se è recente solo se è maggiore della data di oggi meno un mese
                if (LocalDateTime.parse(jobResult.getCreated_at().trim(), DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z yyyy", Locale.UK)).isAfter(LocalDateTime.now().minusMonths(1L))) {
                    countryPosition.incrementRecentWorks();
                }
                // partTime o full time
                if (jobResult.getType().equalsIgnoreCase(FULL_TIME)) {
                    countryPosition.incrementFullTime();
                }
                // controlla gli altri linguaggi
                // siccome non c'è un template riconoscibile all'interno delle descrizioni cerchiamo
                // direttamente gli altri linguaggi che abbiamo configurato
                for (String langToSearch : otherLangToSearch) {
                    if (jobResult.getDescription().toLowerCase().contains(langToSearch.toLowerCase())) {
                        countryPosition.getOtherLanguage().add(langToSearch);
                    }
                }
                // rimuoviamo quelli già inseriti
                if (!countryPosition.getOtherLanguage().isEmpty()) {
                    otherLangToSearch.removeAll(countryPosition.getOtherLanguage());
                }
            }
        }
        return countryPosition;

    }

    // Recursive la funzione eseguirà una call finchè la risposta non è > 50
    private void executeCallWithPage(JobSearchFilter filter, List<JobResult> search, int page) throws UnexpectedException {
        final List<JobResult> results = client.search(filter, page);
        search.addAll(results);
        if (results.size() > 50) {
            executeCallWithPage(filter, search, ++page);
        }
    }

    /**
     * Trova lavori tramite le coordinate e ne calcola le statistiche
     *
     * @param lat Latitudine
     * @param lon Longitudine
     * @return CountryPosition dei luoghi inseriti in input tramite coordinate
     * @throws UnexpectedException - nel caso in cui ci fosse un errore del client
     * @see CountryPosition
     */
    public CountryPosition findByCoords(double lat, double lon) throws UnexpectedException {
        JobSearchFilter filter = new JobSearchFilter();
        filter.setLat(lat);
        filter.setLon(lon);
        final CountryPosition stats = findByFilter(filter);
        if (!stats.getJobFound().isEmpty()) {
            stats.setCountry(stats.getJobFound().get(0).getLocation());
        }
        return stats;
    }

    /**
     * Trova i lavori con i risultati migliori
     *
     * @param numberOfResults numero dei risultati migliori da mostrare
     * @param countries       lista dei paesi
     * @return List of CountryPosition
     * @throws UnexpectedException - nel caso in cui ci fosse un errore del client
     * @see CountryPosition
     */
    public List<CountryPosition> filterBestResults(int numberOfResults, String... countries) throws UnexpectedException {
        final List<CountryPosition> founds = findByLocations(countries);
        if (founds.size() > numberOfResults) {
            return founds.subList(0, numberOfResults);
        } else return founds;
    }
}
