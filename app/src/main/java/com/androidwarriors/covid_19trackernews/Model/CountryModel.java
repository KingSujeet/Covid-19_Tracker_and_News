package com.androidwarriors.covid_19trackernews.Model;

public class CountryModel {

    private String flag, country, cases, toadyCases, deaths, todayDeaths, recovered, active;


    public CountryModel(String flag, String country, String cases, String toadyCases, String deaths, String todayDeaths, String recovered, String active) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.toadyCases = toadyCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getToadyCases() {
        return toadyCases;
    }

    public void setToadyCases(String toadyCases) {
        this.toadyCases = toadyCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}




