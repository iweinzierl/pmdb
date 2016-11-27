package com.github.iweinzierl.pmdb.cloud.persistence;

import com.github.iweinzierl.pmdb.cloud.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MoviePersistenceService extends CrudRepository<Movie, Long> {
}
