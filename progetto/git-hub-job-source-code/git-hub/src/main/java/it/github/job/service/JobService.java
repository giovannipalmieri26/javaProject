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
     * Initialize api session
     *
     * @throws ConfigurationException in case mandatory propertiy is missing (github_api)
     */
    public JobService() throws ConfigurationException {
        final String property = ConfigurationReader.getInstance().getProperty(GITHUB_API);
        otherLangToSearch = ConfigurationReader.getInstance().getPropertyAsList("language");
        if (property != null) {
            client = new GitHubJobClient(property);
        } else throw new ConfigurationException("Github Url missing");
    }


    /**
     * Find Jobs Stats by Locations
     *
     * @param countries List of country ( max 10 entries)
     * @return List of CountryPosition sorted by recentWorks DESC
     * @throws UnexpectedException - Case some problem with client call
     * @see CountryPosition
     */
    public List<CountryPosition> findByLocations(String... countries) throws UnexpectedException {
        // FIXME Controlliamo che siano minori di 10
        if (countries == null || countries.length == 0 || countries.length > 10) {
            final String invalidCountryMessage = "Invalid countries input size ";
            log.log(Level.SEVERE, invalidCountryMessage);
            throw new IllegalArgumentException(invalidCountryMessage);
        }
        log.fine("Find job by location with input: " + Arrays.toString(countries));

        List<CountryPosition> results = new ArrayList<>();
        JobSearchFilter filter = new JobSearchFilter();
        // FIXME Facciamo una ricerca per ogni country e ne calcoliamo le statistiche richieste
        for (String country : countries) {
            filter.setLocation(country);
            final CountryPosition byFilter = findByFilter(filter);
            byFilter.setCountry(country);
            log.fine("Found " + byFilter.getJobFound().size() + " job for country: " + country);
            results.add(byFilter);
        }
        // let's order result by most recent work number
        results.sort(Comparator.comparingInt(CountryPosition::getRecentWorks).reversed());
        return results;
    }

    /**
     * Execute search with filters and calculate stats
     *
     * @param filter Filter for Github API
     * @return CountryPosition of jobs result
     * @throws UnexpectedException - case client error
     * @see CountryPosition
     */
    private CountryPosition findByFilter(JobSearchFilter filter) throws UnexpectedException {
        final List<JobResult> search = new ArrayList<>();
        executeCallWithPage(filter, search, 0);
        CountryPosition countryPosition = new CountryPosition();
        countryPosition.setJobFound(search);
        if (!search.isEmpty()) {
            for (JobResult jobResult : search) {
                // stats
                // recent if is created in last month
                // FIXME capiamo se è recente solo se è maggiore della data di oggi meno un mese
                if (LocalDateTime.parse(jobResult.getCreated_at().trim(), DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z yyyy", Locale.UK)).isAfter(LocalDateTime.now().minusMonths(1L))) {
                    countryPosition.incrementRecentWorks();
                }
                // partTime or full time
                if (jobResult.getType().equalsIgnoreCase(FULL_TIME)) {
                    countryPosition.incrementFullTime();
                }
                // check other Language
                // FIXME Siccome non c'è un template riconoscibile all'interno delle descrizioni cerchiamo direttamente gli altri linguaggi che abbiamo configurato
                for (String langToSearch : otherLangToSearch) {
                    if (jobResult.getDescription().toLowerCase().contains(langToSearch.toLowerCase())) {
                        countryPosition.getOtherLanguage().add(langToSearch);
                    }
                }
                // FIXME rimuoviamo quelli già inseriti
                if (!countryPosition.getOtherLanguage().isEmpty()) {
                    otherLangToSearch.removeAll(countryPosition.getOtherLanguage());
                }
            }
        }
        return countryPosition;

    }

    // Recursive function will execute call until response are > 50
    private void executeCallWithPage(JobSearchFilter filter, List<JobResult> search, int page) throws UnexpectedException {
        final List<JobResult> results = client.search(filter, page);
        search.addAll(results);
        if (results.size() > 50) {
            executeCallWithPage(filter, search, ++page);
        }
    }

    /**
     * Find jobs by input coordinates and calculate stats for it
     *
     * @param lat Latitude
     * @param lon Longitude
     * @return CountryPosition for input location by coordinates
     * @throws UnexpectedException - case client error
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
     * Find jobs with best results
     *
     * @param numberOfResults Number of best results to show
     * @param countries       list of countries
     * @return List of CountryPosition
     * @throws UnexpectedException - case client error
     * @see CountryPosition
     */
    public List<CountryPosition> filterBestResults(int numberOfResults, String... countries) throws UnexpectedException {
        final List<CountryPosition> founds = findByLocations(countries);
        if (founds.size() > numberOfResults) {
            return founds.subList(0, numberOfResults);
        } else return founds;
    }
}
