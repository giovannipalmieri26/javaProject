package it.github.job.model;


/**
 * Class represent api response
 */
public class JobResult {

    private String id;
    private String type;
    private String url;
    private String created_at;
    private String company;
    private String company_url;
    private String location;
    private String title;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_url() {
        return company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    // To string directly in json format
    public String toString() {
        return "{"
                + "                        \"id\":\"" + id + "\""
                + ",                         \"type\":\"" + type + "\""
                + ",                         \"url\":\"" + url + "\""
                + ",                         \"created_at\":\"" + created_at + "\""
                + ",                         \"company\":\"" + company + "\""
                + ",                         \"company_url\":\"" + company_url + "\""
                + ",                         \"location\":\"" + location + "\""
                + ",                         \"title\":\"" + title + "\""
                + ",                         \"description\":\"" + description + "\""
                + "}";
    }
}
