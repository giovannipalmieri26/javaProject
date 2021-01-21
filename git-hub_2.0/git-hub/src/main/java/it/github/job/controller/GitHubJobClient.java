package it.github.job.controller;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import it.github.job.model.JobResult;
import it.github.job.model.JobSearchFilter;

/**
 * Controller per richiedere informazioni da GitHub job API
 */
public class GitHubJobClient {

    private static final Logger log = Logger.getLogger(GitHubJobClient.class.getName());

    private final String url;


    /**
     * Costruttore con URL obbligatorio
     *
     * @param url url del Github Api deve essere valido e non nullo
     */
    public GitHubJobClient(String url) {
        //Controlliamo che l'url non sia vuoto
        if (url == null || url.trim().isEmpty()) {
            log.log(Level.SEVERE, "Empty or null URL");
            throw new IllegalArgumentException("url cannot be null");
        }
        //Controlliamo che l'url sia un url valido
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
     * Ricerca tramite i parametri di input
     *
     * @param filter Class per filter
     * @return Lista di posizioni trovati per i campi inseriti in input
     * @throws UnexpectedException nel caso in cui il response code non è 200
     * @see JobResult
     */
    public List<JobResult> search(JobSearchFilter filter) throws UnexpectedException {
        return search(filter, null);
    } 
    
    /**
     * Ricerca tramite parametri di input e di pagina
     *
     * @param filter Class per filter
     * @param page numero di pagine
     * @return Lista di posizioni trovate per i campi inseriti in input
     * @throws UnexpectedException nel caso in cui il response code non è 200
     * @see JobResult
     */
    public List<JobResult> search(JobSearchFilter filter, Integer page) throws UnexpectedException {
        // dato che l'api risponde solo a 50 risultati potrebbe essere necessario effetturare una chiamata aggiuntiva
        
      List<JobResult> listResult = new ArrayList<JobResult>();
      final Map<String, Object> map = buildFilter(filter);
        if (page != null && page != 0) {
            map.put("page", page.toString());
        }
        String urlSearch=url;
        
        String filtriStr="";
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
          Map.Entry filtro = (Map.Entry) iterator.next();
          filtriStr+=(String)filtro.getKey()+"="+filtro.getValue().toString()+"&";
        }
        if(!filtriStr.isEmpty()){
          filtriStr=filtriStr.substring(0, filtriStr.length()-1);
          urlSearch+="?"+filtriStr;
        }
        
       
        try {
           HttpURLConnection openConnection = (HttpURLConnection) new URL(urlSearch).openConnection();
            openConnection.setRequestMethod("GET");
            openConnection.setRequestProperty("Content-Type", "application/json");
            openConnection.setRequestProperty("Accept", "application/json");
             InputStream in = openConnection.getInputStream();
            String data = "";
            String line = "";
            try {
                InputStreamReader inR = new InputStreamReader(in);
                BufferedReader buf = new BufferedReader(inR);

 

                while ((line = buf.readLine()) != null) {
                    data += line;
                }
            } finally {
                in.close();
            }
            Object obj = JSONValue.parse(data);
            JSONArray array = (JSONArray)obj;
       
            for (int i = 0; i < array.size(); i++) {
              JSONObject jobResJson = (JSONObject) array.get(i);
              JobResult job=new JobResult();
              job.setId((String)jobResJson.get("id"));
              job.setType((String)jobResJson.get("type"));
              job.setUrl((String)jobResJson.get("url"));
              job.setCreated_at((String)jobResJson.get("created_at"));
              job.setCompany((String)jobResJson.get("company"));
              job.setCompany_url((String)jobResJson.get("company_url"));
              job.setLocation((String)jobResJson.get("location"));
              job.setTitle((String)jobResJson.get("title"));
              job.setDescription((String)jobResJson.get("description"));
            
              listResult.add(job);
              
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return listResult;
    }

    /**
     * Converte filter class in query params
     *
     * @param filter Filter class
     * @return filters come query params in Map
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
