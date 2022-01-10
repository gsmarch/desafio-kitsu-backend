package com.challenge.kitsuchallenge.controller;

import com.challenge.kitsuchallenge.model.Episode;
import com.challenge.kitsuchallenge.service.AnimeService;
import com.challenge.kitsuchallenge.service.EpisodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/episode")
public class EpisodeController {
    Episode episode;
    
    @Autowired
    EpisodeService es;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAnimeById(@PathVariable long id) {
        return es.findById(id);
    }

    @GetMapping("/filter-id")
    public ResponseEntity<?> getEpisodeByAnimeIdFilter(@RequestParam String animeId, String episodeNumber) {
        return es.findByFilterID(animeId, episodeNumber);
    }

    @GetMapping("/filter-name")
    public ResponseEntity<?> getEpisodeByAnimeNameFilter(@RequestParam String animeName, String episodeNumber) {
        AnimeService as = new AnimeService();
        Long id = as.findAnimeIdByName(animeName);
        if (id == 0l) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return es.findByFilterID(String.valueOf(id), episodeNumber);
    }

}
