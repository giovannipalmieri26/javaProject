package it.github.job.view;

import it.github.job.model.CountryPosition;
import it.github.job.service.JobService;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Scanner;

/**
 * Main class
 */
public class App {

    static JobService service;
    static Scanner in = new Scanner(System.in); // per leggere in input
    private static int retry = 0; // per evitare troppi errori inaspettati

    public static void main(String[] args) {
        try {
            service = new JobService();
        } catch (Throwable e) {
            System.out.println(" Errore durante l'inizializzaione: " + e.getMessage());
            return;
        }
        System.out.println("**********************");
        System.out.println("* GIT HUB JOB FINDER *");
        System.out.println("***********************");
        System.out.println();
        executeChoose();

    }

    private static void executeChoose() {
        System.out.println();
        System.out.println("Scegli una delle seguenti opzioni: ");
        System.out.println("1. Ricerca per paese (max 10)");
        System.out.println("2. Ricerca per coordinate (max 1) ");
        System.out.println("3. Esci ");
        System.out.println();
        final int choose = in.nextInt();
        try {
            switch (choose) {
                case 1:
                    byLocation();
                    break;
                case 2:
                    byCoords();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opzione Invalida");
                    executeChoose();
            }
        } catch (Throwable e) {
            if (retry < 3) {
                retry++;
                System.out.println("Qualcosa è andato storto, riprova");
                executeChoose();
            } else {
                System.out.println("Esecuzione interrotta per problemi tecnici, controllare i log");
            }
        }
    }

    private static void byCoords() throws UnexpectedException {
        System.out.println("Inserisci la latitudine");
        final double lat = in.nextDouble();
        System.out.println("Inserisci la longitudine");
        final double lon = in.nextDouble();
        final CountryPosition byCoords = service.findByCoords(lat, lon);
        if (byCoords == null) {
            System.out.println("Purtroppo non sono stati trovati posizioni aperti per le coordinate impostate");
        } else {
            show(byCoords);
        }
        executeChoose();
    }

    private static void byLocation() throws UnexpectedException {
        System.out.println("Inserisci il numero di paesi che vuoi cercare:");
        int i = in.nextInt();
        if (i > 10 || i <= 0) {
            System.out.println("Numero invalido, massimo 10 paesi e minimo 1");
            byLocation();
        }
        String[] countries = new String[i];
        do {
            System.out.println("Inserisci il paese: ");
            countries[i - 1] = in.next();
            i--;
        } while (i != 0);
        System.out.println(" Vuoi vedere solo i risultati migliori? (Y/N)");
        final String yes = in.next();
        if (yes.equalsIgnoreCase("y")) {
            System.out.println("Inserisci il numero di risultati migliori da mostrare: ");
            final int bestResults = in.nextInt();
            final List<CountryPosition> countryPositions = service.filterBestResults(bestResults, countries);
            show(countryPositions);
        } else {
            show(service.findByLocations(countries));
        }
        executeChoose();
    }

    private static void show(List<CountryPosition> countryPositions) {
        for (CountryPosition countryPosition : countryPositions) {
            show(countryPosition);
        }
    }

    private static void show(CountryPosition countryPosition) {
        System.out.println(" Paese: " + countryPosition.getCountry());
        System.out.println(" Lavori Trovati : " + countryPosition.getJobFound().size());
        System.out.println(" di Cui Recenti : " + countryPosition.getRecentWorks());
        if(countryPosition.getJobFound().size() != 0)
        System.out.println(" Percentuale Full-Time : " + (countryPosition.getFullTime() * 100 / countryPosition.getJobFound().size()));
        if (!countryPosition.getOtherLanguage().isEmpty()) {
            System.out.println(" Altri Linguaggi richiesti" + countryPosition.getOtherLanguage());
        }
        System.out.println();
    }
}
