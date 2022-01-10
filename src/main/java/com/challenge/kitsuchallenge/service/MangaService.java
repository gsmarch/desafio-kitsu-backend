package com.challenge.kitsuchallenge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

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

    public ResponseEntity<?> findById(long id) {

        RestTemplate template = new RestTemplate();
        try {
            String url = baseUrl+"/"+String.valueOf(id);
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);
            
            return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> findByTrending() {

        RestTemplate template = new RestTemplate();
        try {
            String url = "https://kitsu.io/api/edge/trending/manga";
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);
            
            return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> findByFilter(String attribute, String value) {

        if (attribute.isEmpty() || value.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RestTemplate template = new RestTemplate();
        try {
            String url = baseUrl+"?filter[{attribute}]={value}";
            ResponseEntity<?> entity = template.getForEntity(url.replace("{attribute}", attribute).replace("{value}", value), String.class);

            String result = entity.getBody().toString();
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);
            
            return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
