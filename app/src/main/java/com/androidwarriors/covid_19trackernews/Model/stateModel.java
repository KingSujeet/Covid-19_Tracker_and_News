package com.androidwarriors.covid_19trackernews.Model;

public class stateModel {

    private String active, cured, death, total, name;

    public stateModel(String active, String cured, String death, String total, String name) {
        this.active = active;
        this.cured = cured;
        this.death = death;
        this.total = total;
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCured() {
        return cured;
    }

    public void setCured(String cured) {
        this.cured = cured;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
