package com.challenge.kitsuchallenge.util;

import java.util.ArrayList;
import java.util.List;

import com.challenge.kitsuchallenge.model.Episode;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class EpisodeUtil {
    public static Episode makeEpisode(JsonObject obj) {
        JsonObject data = obj.get("data").getAsJsonObject();
        
        String eID = "";
        String title = "";
        String synopsis = "";
        String airDate = "";
        String seasonNumber = "";
        String durationInMinutes = "";
        
        try {
            if(data != null) {
                eID = obj.get("data").getAsJsonObject().get("id").isJsonNull() ? "" : obj.get("data").getAsJsonObject().get("id").getAsString();
                JsonObject attributes = data.get("attributes").getAsJsonObject();
                if(attributes != null){
                    title = attributes.get("canonicalTitle").isJsonNull() ? "" : attributes.get("canonicalTitle").getAsString();
                    synopsis = attributes.get("synopsis").isJsonNull() ? "" : attributes.get("synopsis").getAsString();
                    airDate =  attributes.get("airdate").isJsonNull() ? "" : attributes.get("airdate").getAsString();
                    seasonNumber = attributes.get("seasonNumber").isJsonNull() ? "" : attributes.get("seasonNumber").getAsString();
                    durationInMinutes = attributes.get("length").isJsonNull() ? "" : attributes.get("length").getAsString();
                }
            }
            
            return new Episode(eID, title, synopsis, airDate, seasonNumber, durationInMinutes);

        } catch (Exception e) {
            return null;
        }
    }

    public static List<Episode> makeEpisodeList(JsonObject obj) {
        List<Episode> listaEpisodes = new ArrayList<Episode>();
        JsonArray entry = obj.getAsJsonArray("data");
        entry.forEach(item -> {
            JsonObject data = (JsonObject) item;
            String eID = "";
            String title = "";
            String synopsis = "";
            String airDate = "";
            String seasonNumber = "";
            String durationInMinutes = "";

            if(data != null) {
                eID = data.get("id") == null ? "" : data.get("id").getAsString();
                JsonObject attributes = data.get("attributes").getAsJsonObject();
                if(attributes != null) {
                    title = attributes.get("canonicalTitle").isJsonNull() ? "" : attributes.get("canonicalTitle").getAsString();
                    synopsis = attributes.get("synopsis").isJsonNull() ? "" : attributes.get("synopsis").getAsString();
                    airDate =  attributes.get("airdate").isJsonNull() ? "" : attributes.get("airdate").getAsString();
                    seasonNumber = attributes.get("seasonNumber").isJsonNull() ? "" : attributes.get("seasonNumber").getAsString();
                    durationInMinutes = attributes.get("length").isJsonNull() ? "" : attributes.get("length").getAsString();
                }
            }
            listaEpisodes.add(new Episode(eID, title, synopsis, airDate, seasonNumber, durationInMinutes));
        });
        return listaEpisodes;
    }
}
