package com.challenge.kitsuchallenge.controller;

import java.util.List;

import com.challenge.kitsuchallenge.model.Manga;
import com.challenge.kitsuchallenge.service.MangaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/manga")
public class MangaController {
    Manga manga;

    @Autowired
    MangaService ms;

    @GetMapping(path = "/{id}")
    public Manga getAnimeById(@PathVariable long id) {
        return ms.findById(id);
    }

    @GetMapping("/trending")
    public List<Manga> getAnimeTrending(){
        return ms.findByTrending();
    }

    @GetMapping("/filter")
    public List<Manga> getAnimeFilter(@RequestParam String attribute, String value) {
        return ms.findByFilter(attribute, value);
    }

}
