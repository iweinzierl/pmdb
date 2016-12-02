package com.github.iweinzierl.pmdb.cloud.controller;

import com.github.iweinzierl.pmdb.cloud.domain.Movie;
import com.github.iweinzierl.pmdb.cloud.exception.MissingPropertyInfo;
import com.github.iweinzierl.pmdb.cloud.persistence.MoviePersistenceService;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc(secure = false)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoviePersistenceService moviePersistenceService;

    @Test
    public void ensureIdIsSetForAddMovie() throws Exception {
        Movie inputMovie = new Movie();
        inputMovie.setTitle("Test Movie");

        Movie savedMovie = new Movie();
        savedMovie.setId(1);
        savedMovie.setTitle(inputMovie.getTitle());

        given(moviePersistenceService.save(inputMovie)).willReturn(savedMovie);
        MockHttpServletRequestBuilder postReq = post("/movies")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new Gson().toJson(inputMovie));

        mockMvc.perform(postReq)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(new Gson().toJson(savedMovie)));
    }

    @Test
    public void ensureNoContentHandledProperly() throws Exception {
        given(moviePersistenceService.save((Movie) null)).willReturn(null);
        MockHttpServletRequestBuilder postReq = post("/movies")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("");

        mockMvc.perform(postReq).andExpect(status().isBadRequest());
    }

    @Test
    public void ensureMissingFieldHandledProperly() throws Exception {
        Movie input = new Movie();
        input.setId(1);

        MissingPropertyInfo errorMsg = new MissingPropertyInfo("Missing property: title", "title", input);

        MockHttpServletRequestBuilder postReq = post("/movies")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new Gson().toJson(input));

        mockMvc.perform(postReq)
                .andExpect(status().isBadRequest())
                .andExpect(content().json(new Gson().toJson(errorMsg)));
    }

    @Test
    public void ensureEmptyMovieListReturnedProperly() throws Exception {
        given(moviePersistenceService.findAll()).willReturn(Lists.newArrayList());

        mockMvc.perform(get("/movies").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void ensureMovieListReturnedProperly() throws Exception {
        Movie out1 = Movie.builder().id(1).title("Movie 1").build();
        Movie out2 = Movie.builder().id(2).title("Movie 2").build();

        given(moviePersistenceService.findAll()).willReturn(Lists.newArrayList(out1, out2));

        mockMvc.perform(get("/movies").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(Lists.newArrayList(out1, out2))));
    }

    @Test
    public void ensureMovieNotFoundHandledProperly() throws Exception {
        given(moviePersistenceService.findOne(1L)).willReturn(null);

        mockMvc.perform(get("/movies/{id}", 1L).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void ensureMovieByIdReturnedProperly() throws Exception {
        Movie out = Movie.builder().id(1).title("Movie 1").build();

        given(moviePersistenceService.findOne(1L)).willReturn(out);

        mockMvc.perform(get("/movies/{id}", 1L).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(out)));
    }

    @Test
    public void ensureDeleteNotExistingMovieHandledProperly() throws Exception {
        given(moviePersistenceService.findOne(1L)).willReturn(null);

        mockMvc.perform(delete("/movies/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void ensureDeleteMovieByIdHandledProperly() throws Exception {
        Movie out = Movie.builder().id(1).title("Movie 1").build();

        given(moviePersistenceService.findOne(1L)).willReturn(out);

        mockMvc.perform(delete("/movies/{id}", 1L).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        Mockito.verify(moviePersistenceService, times(1)).delete(1L);
    }
}