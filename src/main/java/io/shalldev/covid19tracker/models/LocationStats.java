package io.shalldev.covid19tracker.models;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class LocationStats {

    private String state;
    private String country;
    private String lastUpdate;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private Double incidenceRate;
    private Double fatalityRatio;

    public Double getIncidenceRate() {
        return incidenceRate;
    }

    public void setIncidenceRate(String incidenceRate) {
        this.incidenceRate = !StringUtils.isEmpty(incidenceRate) ? roundAvoid(Double.parseDouble(incidenceRate), 3) :
                0.0;
//        this.incidenceRate = incidenceRate;
    }

    public Double getFatalityRatio() {
        return fatalityRatio;
    }

    public void setFatalityRatio(String caseFatalityRatio) {
//        this.fatalityRatio = fatalityRatio;
        this.fatalityRatio = !StringUtils.isEmpty(caseFatalityRatio) ?
                roundAvoid(Double.parseDouble(caseFatalityRatio), 3) :
                0.0;
    }

    public LocationStats() { }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = !StringUtils.isEmpty(confirmed) ? Integer.parseInt(confirmed) : 0;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = !StringUtils.isEmpty(deaths) ? Integer.parseInt(deaths) : 0;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {

        this.recovered = !StringUtils.isEmpty(recovered) ? Integer.parseInt(recovered) : 0;
    }

    public int getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = !StringUtils.isEmpty(active) ? Integer.parseInt(active) : 0;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", deaths='" + deaths + '\'' +
                ", recovered='" + recovered + '\'' +
                ", active='" + active + '\'' +
                ", incidenceRate='" + incidenceRate + '\'' +
                ", fatalityRatio='" + fatalityRatio + '\'' +
                '}';
    }

    private static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
