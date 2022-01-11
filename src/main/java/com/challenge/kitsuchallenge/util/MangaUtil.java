package com.challenge.kitsuchallenge.util;

import java.util.ArrayList;
import java.util.List;

import com.challenge.kitsuchallenge.model.Manga;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MangaUtil {

    public static Manga makeManga(JsonObject obj) {
        JsonObject data = obj.get("data").getAsJsonObject();
        
        String mID = "";
        String slug = "";
        String description = "";
        String volumeCount = "";
        try {
            if(data != null) {
                mID = obj.get("data").getAsJsonObject().get("id").isJsonNull() ? "" : obj.get("data").getAsJsonObject().get("id").getAsString();
                JsonObject attributes = data.get("attributes").getAsJsonObject();
                if(attributes != null){
                    slug = attributes.get("slug").isJsonNull() ? "" : attributes.get("slug").getAsString();
                    description = attributes.get("description").isJsonNull() ? "" : attributes.get("description").getAsString();
                    volumeCount = attributes.get("volumeCount").isJsonNull() ? "" : attributes.get("volumeCount").getAsString();
                }
            }
            
            return new Manga(mID, slug, description, volumeCount);

        } catch (Exception e) {
            return null;
        }
    }

    public static List<Manga> makeMangaList(JsonObject obj) {
        List<Manga> listaManga = new ArrayList<Manga>();
        JsonArray entry = obj.getAsJsonArray("data");
        entry.forEach(item -> {
            JsonObject data = (JsonObject) item;
            String mID = "";
            String slug = "";
            String description = "";
            String volumeCount = "";

            if(data != null) {
                mID = data.get("id") == null ? "" : data.get("id").getAsString();
                JsonObject attributes = data.get("attributes").getAsJsonObject();
                if(attributes != null) {
                    slug = attributes.get("slug").isJsonNull() ? "" : attributes.get("slug").getAsString();
                    description = attributes.get("description").isJsonNull() ? "" : attributes.get("description").getAsString();
                    volumeCount = attributes.get("volumeCount").isJsonNull() ? "" : attributes.get("volumeCount").getAsString();
                }
            }
            listaManga.add(new Manga(mID, slug, description, volumeCount));
        });
        return listaManga;
    }
}
