package com.challenge.kitsuchallenge.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import com.challenge.kitsuchallenge.model.Manga;
import com.challenge.kitsuchallenge.util.MangaUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MangaService {
    
    @Value("${my.property.manga}")
    String baseUrl;

    public Manga findById(long id) {

        RestTemplate template = new RestTemplate();
        try {
            String url = baseUrl+"/"+String.valueOf(id);
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);
            
            Manga manga = MangaUtil.makeManga(obj);

            return manga;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Manga> findByTrending() {

        RestTemplate template = new RestTemplate();
        try {
            String url = "https://kitsu.io/api/edge/trending/manga";
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);
            
            List<Manga> listaManga = MangaUtil.makeMangaList(obj);
            
            return listaManga;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Manga> findByFilter(String attribute, String value) {

        if (attribute.isEmpty() || value.isEmpty()){
            return null;
        }

        RestTemplate template = new RestTemplate();
        try {
            String url = baseUrl+"?filter[{attribute}]={value}";
            ResponseEntity<?> entity = template.getForEntity(url.replace("{attribute}", attribute).replace("{value}", value), String.class);

            String result = entity.getBody().toString();
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);
            
            List<Manga> listaManga = MangaUtil.makeMangaList(obj);

            return listaManga;

        } catch (Exception e) {
            return null;
        }
    }
}
