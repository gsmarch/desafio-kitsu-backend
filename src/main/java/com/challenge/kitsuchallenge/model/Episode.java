package com.challenge.kitsuchallenge.model;

public class Episode extends BaseEntity {

    private String animeName;
    private String id;
    private String title;
    private String synopsis;
    private String airDate;
    private String seasonNumber;
    private String durationInMinutes;

    public Episode() {
        super();
    }

    public Episode(String animeName, String id, String title, String synopsis, String airDate, String seasonNumber,
            String durationInMinutes) {
        this.animeName = animeName;
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.airDate = airDate;
        this.seasonNumber = seasonNumber;
        this.durationInMinutes = durationInMinutes;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(String seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(String durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
    
}
