package com.underdogdeveloper.codecontests.model;

public class Contest {
    String name = "";
    String url = "";
    String start_time = "";
    long duration = 0;
    String site ="";

    public Contest() {
    }

    public Contest(String name, String url, String start_time, long duration, String site){
        this.name = name;
        this.url = url;
        this.start_time = start_time;
        this.duration = duration;
        this.site = site;
    }

    public Contest(String name, String url, String site) {
        this.name = name;
        this.url = url;
        this.start_time = start_time;
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }


}
