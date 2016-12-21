package com.github.iweinzierl.pmdb.cloud.controller;

import com.github.iweinzierl.pmdb.cloud.domain.Format;
import com.github.iweinzierl.pmdb.cloud.persistence.MoviePersistenceService;
import com.github.iweinzierl.springbootlogging.annotation.HttpLogging;
import com.github.iweinzierl.springbootlogging.annotation.LoggingOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/formats")
public class FormatController {

    private final MoviePersistenceService moviePersistenceService;

    @Autowired
    public FormatController(MoviePersistenceService moviePersistenceService) {
        this.moviePersistenceService = moviePersistenceService;
    }

    @HttpLogging(options = {LoggingOption.REQUEST})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public List<Format> listFormats() {
        return moviePersistenceService.findDistinctFormat();
    }
}
