package io.shalldev.covid19tracker.services;

import io.shalldev.covid19tracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService {

//    @Value( "${virus.data.source}" )
    private String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/";

    private List<LocationStats> allStats = new ArrayList<>();

    private int confirmedCases, activeCases, deathsCases, recoveredCases = 0;

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public int getDeathsCases() {
        return deathsCases;
    }

    public int getRecoveredCases() {
        return recoveredCases;
    }

    public int getConfirmedCases() {
        return confirmedCases;
    }

    @PostConstruct
    @Scheduled(cron = "${cron.expression}")
    public void fetchVirusData() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getValidVirusDataUrl(VIRUS_DATA_URL)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Reader csvBodyData = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyData);

        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province_State"));
            locationStat.setCountry(record.get("Country_Region"));
            locationStat.setLastUpdate(record.get("Last_Update"));
            locationStat.setConfirmed(record.get("Confirmed"));
            locationStat.setActive(record.get("Active"));
            locationStat.setDeaths(record.get("Deaths"));
            locationStat.setRecovered(record.get("Recovered"));
            locationStat.setIncidenceRate(record.get("Incidence_Rate"));
            locationStat.setCaseFatalityRatio(record.get("Case-Fatality_Ratio"));

            confirmedCases += locationStat.getConfirmed();
            activeCases += locationStat.getActive();
            deathsCases += locationStat.getDeaths();
            recoveredCases += locationStat.getRecovered();

            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }

    private String getValidVirusDataUrl(String urlData) throws IOException {

        String lastAvailableUrlData = String.format(
                "%s%s.csv",
                urlData,
                LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));

        HttpURLConnection con = (HttpURLConnection) new URL(lastAvailableUrlData).openConnection();
        con.setRequestMethod("HEAD");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return lastAvailableUrlData;
        }
        return String.format(
                "%s%s.csv",
                urlData,
                LocalDateTime.now().minusDays(2).format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
    }
}
