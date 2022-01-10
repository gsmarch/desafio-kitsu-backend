package com.challenge.kitsuchallenge.model;
//import javax.persistence.Entity;

//@Entity
public class Anime extends BaseEntity {

    private String id;
    private String slug;
    private String description;
    private String episodeCount;

    public Anime() {
        super();
    }

    public Anime(String id, String slug, String description, String episodeCount) {
        this.id = id;
        this.slug = slug;
        this.description = description;
        this.episodeCount = episodeCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(String episodeCount) {
        this.episodeCount = episodeCount;
    }

}
