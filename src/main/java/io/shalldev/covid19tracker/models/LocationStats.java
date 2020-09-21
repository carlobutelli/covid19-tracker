package io.shalldev.covid19tracker.models;

import org.springframework.util.StringUtils;

public class LocationStats {

    private String state;
    private String country;
    private String lastUpdate;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private String incidenceRate;
    private String caseFatalityRatio;

    public String getIncidenceRate() {
        return incidenceRate;
    }

    public void setIncidenceRate(String incidenceRate) {
        this.incidenceRate = incidenceRate;
    }

    public String getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(String caseFatalityRatio) {
        this.caseFatalityRatio = caseFatalityRatio;
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
                ", lastUpdate='" + lastUpdate + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", deaths='" + deaths + '\'' +
                ", recovered='" + recovered + '\'' +
                ", active='" + active + '\'' +
                ", incidenceRate='" + incidenceRate + '\'' +
                ", caseFatalityRatio='" + caseFatalityRatio + '\'' +
                '}';
    }
}
