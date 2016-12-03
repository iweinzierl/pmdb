package com.github.iweinzierl.pmdb.cloud.persistence;

import com.github.iweinzierl.pmdb.cloud.domain.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenrePersistenceService extends CrudRepository<Genre, String> {

    @Query("SELECT DISTINCT name FROM Genre")
    List<String> findDistinctGenre();
}
