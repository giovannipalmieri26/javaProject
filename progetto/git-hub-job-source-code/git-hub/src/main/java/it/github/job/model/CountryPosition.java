package it.github.job.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent number of positions present into a country with:
 * - Name of country
 * - job Found Details
 * - Number of recent works
 * - Number of fullTime
 * - List of other Language
 */
public class CountryPosition {
    private String country;
    private List<JobResult> jobFound;
    private Integer recentWorks = 0;
    private int fullTime = 0;
    private List<String> otherLanguage;

    public Integer getRecentWorks() {
        return recentWorks;
    }

    public void setRecentWorks(Integer recentWorks) {
        this.recentWorks = recentWorks;
    }

    public int getFullTime() {
        return fullTime;
    }

    public void setFullTime(int fullTime) {
        this.fullTime = fullTime;
    }

    // null safe
    public List<String> getOtherLanguage() {
        if (otherLanguage == null) {
            otherLanguage = new ArrayList<>();
        }
        return otherLanguage;
    }

    public void setOtherLanguage(List<String> otherLanguage) {
        this.otherLanguage = otherLanguage;
    }

    public List<JobResult> getJobFound() {
        return jobFound;
    }

    public void setJobFound(List<JobResult> jobFound) {
        this.jobFound = jobFound;
    }

    public void incrementRecentWorks() {
        this.recentWorks++;
    }

    public void incrementFullTime() {
        this.fullTime++;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "{"
                + "                        \"country\":\"" + country + "\""
                + ",                         \"recentWorks\":\"" + recentWorks + "\""
                + ",                         \"fullTime\":\"" + fullTime + "\""
                + ",                         \"otherLanguage\":" + otherLanguage
                + "}";
    }
}
