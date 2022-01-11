package com.challenge.kitsuchallenge.util;

import java.util.ArrayList;
import java.util.List;

import com.challenge.kitsuchallenge.model.Anime;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AnimeUtil {

    public static Anime makeAnime(JsonObject obj) {
        JsonObject data = obj.get("data").getAsJsonObject();
        
        String aID = "";
        String slug = "";
        String description = "";
        String episodeCount = "";
        try {
            if(data != null) {
                aID = obj.get("data").getAsJsonObject().get("id").isJsonNull() ? "" : obj.get("data").getAsJsonObject().get("id").getAsString();
                JsonObject attributes = data.get("attributes").getAsJsonObject();
                if(attributes != null){
                    slug = attributes.get("slug").isJsonNull() ? "" : attributes.get("slug").getAsString();
                    description = attributes.get("description").isJsonNull() ? "" : attributes.get("description").getAsString();
                    episodeCount = attributes.get("episodeCount").isJsonNull() ? "" : attributes.get("episodeCount").getAsString();
                }
            }
            
            return new Anime(aID, slug, description, episodeCount);

        } catch (Exception e) {
            return null;
        }
    }

    public static List<Anime> makeAnimeList(JsonObject obj) {
        List<Anime> listaAnime = new ArrayList<Anime>();
        JsonArray entry = obj.getAsJsonArray("data");
        entry.forEach(item -> {
            JsonObject data = (JsonObject) item;
            String aID = "";
            String slug = "";
            String description = "";
            String episodeCount = "";

            if(data != null) {
                aID = data.get("id") == null ? "" : data.get("id").getAsString();
                JsonObject attributes = data.get("attributes").getAsJsonObject();
                if(attributes != null) {
                    slug = attributes.get("slug").isJsonNull() ? "" : attributes.get("slug").getAsString();
                    description = attributes.get("description").isJsonNull() ? "" : attributes.get("description").getAsString();
                    episodeCount = attributes.get("episodeCount").isJsonNull() ? "" : attributes.get("episodeCount").getAsString();
                }
            }
            listaAnime.add(new Anime(aID, slug, description, episodeCount));
        });
        return listaAnime;
    }
}
