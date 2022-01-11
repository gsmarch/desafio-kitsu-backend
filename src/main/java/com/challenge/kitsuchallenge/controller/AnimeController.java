package com.challenge.kitsuchallenge.controller;

import java.util.List;

import com.challenge.kitsuchallenge.model.Anime;
import com.challenge.kitsuchallenge.service.AnimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/anime")
public class AnimeController {
    Anime anime;

    @Autowired
    AnimeService as;

    @GetMapping(path = "/{id}")
    public Anime getAnimeById(@PathVariable long id) {
        return as.findById(id);
    }

    @GetMapping("/trending")
    public List<Anime> getAnimeTrending(){
        return as.findByTrending();
    }

    @GetMapping("/filter")
    public List<Anime> getAnimeFilter(@RequestParam String attribute, String value) {
        return as.findByFilter(attribute, value);
    }

}
