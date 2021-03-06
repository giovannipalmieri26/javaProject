package it.github.job.controller;


import it.github.job.model.JobResult;
import it.github.job.model.JobSearchFilter;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller for retrieve information from GitHub job API
 */
public class GitHubJobClient {

    private static final Logger log = Logger.getLogger(GitHubJobClient.class.getName());

    private final String url;


    /**
     * Constructor with mandatory url
     *
     * @param url url of Github Api must be not null and valid
     */
    public GitHubJobClient(String url) {
        // FIXME Controlliamo che url non sia vuoto
        if (url == null || url.trim().isEmpty()) {
            log.log(Level.SEVERE, "Empty or null URL");
            throw new IllegalArgumentException("url cannot be null");
        }
        // FIXME Controlliamo che l úrl sia un url valido
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            log.log(Level.SEVERE, "Invalid URL: " + url, e);
            throw new IllegalArgumentException("Url must be valid url");
        }
        this.url = url;
        log.info("Github Job client initialized with url: " + url);
    }

    /**
     * Search by input parameters
     *
     * @param filter Class for filter
     * @return List of Position found for input fields
     * @throws UnexpectedException case of response code isn't 200
     * @see JobResult
     */
    public List<JobResult> search(JobSearchFilter filter) throws UnexpectedException {
        return search(filter, null);
    }

    /**
     * Search by input parameters and page
     *
     * @param filter Class for filter
     * @param page number of page
     * @return List of Position found for input fields
     * @throws UnexpectedException case of response code isn't 200
     * @see JobResult
     */
    public List<JobResult> search(JobSearchFilter filter, Integer page) throws UnexpectedException {
        // FIXME dato che la api risponde solo 50 risultati potrebbe essere necessario effetturare una chiamata aggiuntiva
        final Map<String, Object> map = buildFilter(filter);
        if (page != null && page != 0) {
            map.put("page", page.toString());
        }
        final HttpResponse<List<JobResult>> jsonNodeHttpResponse = Unirest.get(url).queryString(map).asObject(new GenericType<List<JobResult>>() {
        });
        if (jsonNodeHttpResponse.isSuccess()) {
            final List<JobResult> body = jsonNodeHttpResponse.getBody();
            log.fine(" GitHub Job Api response: " + body);
            return body;
        } else {
            final String errorMessage = "Github Job Api response: " + jsonNodeHttpResponse.getStatus() + " with status: " + jsonNodeHttpResponse.getStatusText();
            log.log(Level.SEVERE, errorMessage);
            throw new UnexpectedException(errorMessage);
        }
    }

    /**
     * Convert filter class into query params
     *
     * @param filter Filter class
     * @return filters as query params in a Map
     */
    private Map<String, Object> buildFilter(JobSearchFilter filter) {
        Map<String, Object> query = new HashMap<>();
        if (filter.getDescription() != null) {
            query.put("search", filter.getDescription());
        }
        if (filter.getFullTime() != null) {
            query.put("full_time", filter.getFullTime());
        }
        if (filter.getLocation() != null && !filter.getLocation().trim().isEmpty()) {
            query.put("location", filter.getLocation().trim());
        } else if (filter.getLat() != null && filter.getLon() != null) {
            query.put("lat", filter.getLat());
            query.put("long", filter.getLon());
        } else {
            final String errorMessage = "One of Location or Longitude/Latitude is mandatory";
            log.log(Level.SEVERE, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        log.fine(" Filters: " + filter);
        return query;
    }
}
