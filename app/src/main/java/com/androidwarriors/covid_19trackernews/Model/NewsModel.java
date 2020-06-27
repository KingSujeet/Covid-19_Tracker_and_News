package com.androidwarriors.covid_19trackernews.Model;

public class NewsModel {

    private String title, description, url, flag;

    public NewsModel(String title, String description, String url, String flag) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.flag = flag;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
