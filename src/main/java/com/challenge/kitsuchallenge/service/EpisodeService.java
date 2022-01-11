package com.challenge.kitsuchallenge.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import com.challenge.kitsuchallenge.model.Episode;
import com.challenge.kitsuchallenge.util.EpisodeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EpisodeService {
    
    @Value("${my.property.episode}")
    String baseUrl;

    public Episode findById(long id) {

        RestTemplate template = new RestTemplate();
        try {
            String url = baseUrl+"/"+String.valueOf(id);
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);

            Episode episode = EpisodeUtil.makeEpisode(obj);

            return episode;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Episode> findByFilterID(String animeID, String episodeNumber) {
        if (animeID.isEmpty() || episodeNumber.isEmpty()){
            return null;
        }

        RestTemplate template = new RestTemplate();
        try {
            String url = "https://kitsu.io/api/edge/anime/"+animeID+"/episodes?filter[number]="+episodeNumber;
            ResponseEntity<?> entity = template.getForEntity(url, String.class);

            String result = entity.getBody().toString();
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(result, JsonObject.class);
            
            List<Episode> episodes = EpisodeUtil.makeEpisodeList(obj);

            return episodes;

        } catch (Exception e) {
            return null;
        }
    }
}

