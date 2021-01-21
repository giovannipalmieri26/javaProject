package it.github.job.model;

// Class come input per API
public class JobSearchFilter {

    private String description = "java"; // — I termine di ricerca.
    private String location; // — Il nome della cità, zip code, o un altro termine .
    private Double lat; //  — Una specifica latitudine. Se usata, devi inviare anche un long e non una posizione.
    private Double lon; // — Una specifica longitudine. Se usata, devi inviare anche un long e non una posizione.
    private Boolean fullTime = false; //  — Se vuoi limitare i risultati alle posizioni full time setta questo parametro a 'true'.

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }


    @Override
    // To String directly in json format
    public String toString() {
        return "{"
                + "                        \"description\":\"" + description + "\""
                + ",                         \"location\":" + location
                + ",                         \"lat\":\"" + lat + "\""
                + ",                         \"lon\":\"" + lon + "\""
                + ",                         \"fullTime\":\"" + fullTime + "\""
                + "}";
    }
}
