package com.underdogdeveloper.codecontests.model;

public class Contest {
    int id =0;
    String name = "";
    String type = "";
    String phase = null;
    long durationSeconds = 0;
    long startTimeSeconds = 0;
    long relativeTimeSeconds = 0;
    String websiteUrl = "";

    enum PHASE {
        BEFORE,
        FINISHED
    }

    public Contest(int id, String name, String type, String phase, long durationSeconds, long startTimeSeconds, long relativeTimeSeconds, String websiteUrl ){
        this.id = id;
        this.name = name;
        this.type = type;
        this.phase = phase;
        this.durationSeconds = durationSeconds;
        this.startTimeSeconds = startTimeSeconds;
        this.relativeTimeSeconds = relativeTimeSeconds;
        this.websiteUrl = websiteUrl;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getPhase() {
        return phase;
    }
    public void setPhase(String phase) {
        this.phase = phase;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }
    public void setDurationSeconds(long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public long getStartTimeSeconds() {
        return startTimeSeconds;
    }
    public void setStartTimeSeconds(long startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }

    public long getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }
    public void setRelativeTimeSeconds(long relativeTimeSeconds) {
        this.relativeTimeSeconds = relativeTimeSeconds;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
