package com.github.iweinzierl.pmdb.cloud.controller;

import com.github.iweinzierl.pmdb.cloud.domain.Movie;
import com.github.iweinzierl.pmdb.cloud.exception.MissingPropertyException;
import com.github.iweinzierl.pmdb.cloud.exception.MissingPropertyInfo;
import com.github.iweinzierl.pmdb.cloud.exception.MovieNotFoundException;
import com.github.iweinzierl.pmdb.cloud.persistence.MoviePersistenceService;
import com.github.iweinzierl.springbootlogging.annotation.HttpLogging;
import com.github.iweinzierl.springbootlogging.annotation.LoggingOption;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MoviePersistenceService moviePersistenceService;

    @Autowired
    public MovieController(MoviePersistenceService moviePersistenceService) {
        this.moviePersistenceService = moviePersistenceService;
    }

    @HttpLogging(options = {LoggingOption.REQUEST})
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public Movie addMovie(@RequestBody Movie movie) {
        if (Strings.isNullOrEmpty(movie.getTitle())) {
            throw new MissingPropertyException("title", movie);
        }

        return moviePersistenceService.save(movie);
    }

    @HttpLogging(options = {LoggingOption.REQUEST})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public List<Movie> listMovies() {
        return StreamSupport
                .stream(moviePersistenceService.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @HttpLogging(options = {LoggingOption.REQUEST})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET, path = "/{id}")
    public Movie getMovie(@PathVariable("id") Long id) {
        Movie movie = moviePersistenceService.findOne(id);

        if (movie == null) {
            throw new MovieNotFoundException(id);
        }

        return movie;
    }

    @HttpLogging(options = {LoggingOption.REQUEST})
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        Movie movie = moviePersistenceService.findOne(id);

        if (movie == null) {
            throw new MovieNotFoundException(id);
        } else {
            moviePersistenceService.delete(id);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingPropertyException.class)
    @ResponseBody
    private MissingPropertyInfo handleMissingPropertyException(HttpServletRequest req, MissingPropertyException ex) {
        return new MissingPropertyInfo(ex.getMessage(), ex.getProperty(), ex.getObject());
    }
}
