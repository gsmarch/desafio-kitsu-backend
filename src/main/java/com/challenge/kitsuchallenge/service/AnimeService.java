package com.challenge.kitsuchallenge.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import com.challenge.kitsuchallenge.model.Anime;
import com.challenge.kitsuchallenge.util.AnimeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnimeService {
    
    @Value("${my.property.anime}")
    String baseUrl;

    public Anime findById(long id) {

        RestTemplate template = new RestTemplate();
        try {
            String url = baseUrl+"/"+String.valueOf(id);
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);

            Anime anime = AnimeUtil.makeAnime(obj);

            return anime;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Anime> findByTrending() {

        RestTemplate template = new RestTemplate();
        try {
            String url = "https://kitsu.io/api/edge/trending/anime";
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);

            List<Anime> listaAnime = AnimeUtil.makeAnimeList(obj);
            
            return listaAnime;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Anime> findByFilter(String attribute, String value) {

        if (attribute.isEmpty() || value.isEmpty()){
            return null;
        }

        RestTemplate template = new RestTemplate();
        try {
            String url = baseUrl+"?filter["+attribute+"]="+value;
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);
            
            List<Anime> listaAnime = AnimeUtil.makeAnimeList(obj);

            return listaAnime;

        } catch (Exception e) {
            return null;
        }
    }

    public Long findAnimeIdByName(String name) {
        if (name.isEmpty()){
            return 0l;
        }
        try {
            RestTemplate template = new RestTemplate();
            String url = "https://kitsu.io/api/edge/anime?filter[slug]="+name;
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);

            String entry = obj.getAsJsonArray("data").get(0).getAsJsonObject().get("id").getAsString();
            
            Long id = Long.parseLong(entry);
            return id;
        } catch (Exception e) {
            return 0l;
        }
    }
}
