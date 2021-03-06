package it.github.job.controller;

import it.github.job.model.JobResult;
import it.github.job.model.JobSearchFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.rmi.UnexpectedException;
import java.util.List;

class GitHubJobClientTest {

    private final String url = "https://jobs.github.com/positions.json";

    @Test
    void badParameters() {
        // FIXME testiamo che se manca un parametro obbligatorio ci sia un errore
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            GitHubJobClient client = new GitHubJobClient(url);
            JobSearchFilter filter = new JobSearchFilter();
            client.search(filter);
        });
    }

    @Test
    void badUrl() {
        // FIXME testiamo che se nelle impostazioni ci sia un url errato venga lanciato un errore
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            GitHubJobClient client = new GitHubJobClient("aaaaaaaaa");
        });
    }

    @Test
    void nullUrl() {
        // FIXME testiamo che se non venga passato l'url la classe vada in errore
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            GitHubJobClient client = new GitHubJobClient(null);
        });
    }

    @Test
    void search() throws UnexpectedException {
        // FIXME Testiamo che il client risponda correttamente nel caso gli input siano corretti, non importa che siano vuoti ma solo che non vada in errore
        GitHubJobClient client = new GitHubJobClient(url);
        JobSearchFilter filter = new JobSearchFilter();
        filter.setLocation("california");
        final List<JobResult> searchResult = client.search(filter);
        Assertions.assertNotNull(searchResult);
    }
}
