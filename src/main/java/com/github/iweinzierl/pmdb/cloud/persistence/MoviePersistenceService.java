package com.github.iweinzierl.pmdb.cloud.persistence;

import com.github.iweinzierl.pmdb.cloud.domain.Format;
import com.github.iweinzierl.pmdb.cloud.domain.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoviePersistenceService extends CrudRepository<Movie, Long> {

    @Query("SELECT DISTINCT format FROM Movie")
    List<Format> findDistinctFormat();
}
