package com.challenge.kitsuchallenge.model;

public class Manga extends BaseEntity {

    private String id;
    private String slug;
    private String description;
    private String volumeCount;

    public Manga() {
        super();
    }
    
    public Manga(String id, String slug, String description, String volumeCount) {
        this.id = id;
        this.slug = slug;
        this.description = description;
        this.volumeCount = volumeCount;
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
    public String getVolumeCount() {
        return volumeCount;
    }
    public void setVolumeCount(String volumeCount) {
        this.volumeCount = volumeCount;
    }

    
}
