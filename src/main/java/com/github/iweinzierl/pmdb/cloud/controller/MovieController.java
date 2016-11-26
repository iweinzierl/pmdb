package com.github.iweinzierl.pmdb.cloud.controller;

import com.github.iweinzierl.pmdb.cloud.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.POST, path = "/")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        LOGGER.info("Received movie: {}", movie.toString());
        LOGGER.warn("Not yet implemented");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(movie);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/")
    public List<Movie> listMovies() {
        LOGGER.warn("Not yet implemented");
        return Collections.EMPTY_LIST;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/{id}")
    public Movie getMovie(@PathVariable("id") String id) {
        LOGGER.warn("Not yet implemented");
        return null;
    }
}
