package com.github.iweinzierl.pmdb.cloud.controller;

import com.github.iweinzierl.pmdb.cloud.persistence.GenrePersistenceService;
import com.github.iweinzierl.springbootlogging.annotation.HttpLogging;
import com.github.iweinzierl.springbootlogging.annotation.LoggingOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenrePersistenceService genrePersistenceService;

    @Autowired
    public GenreController(GenrePersistenceService genrePersistenceService1) {
        this.genrePersistenceService = genrePersistenceService1;
    }

    @HttpLogging(options = {LoggingOption.REQUEST})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public List<String> listGenres() {
        return genrePersistenceService.findDistinctGenre();
    }
}
