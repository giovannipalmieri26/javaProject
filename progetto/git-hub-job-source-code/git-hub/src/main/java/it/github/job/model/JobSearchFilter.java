package it.github.job.model;

// Class as input for API
public class JobSearchFilter {

    private String description = "java"; // — A search term, such as "ruby" or "java". This parameter is aliased to search.
    private String location; // — A city name, zip code, or other location search term.
    private Double lat; //  — A specific latitude. If used, you must also send long and must not send location.
    private Double lon; // — A specific longitude. If used, you must also send lat and must not send location.
    private Boolean fullTime = false; //  — If you want to limit results to full time positions set this parameter to 'true'.

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
