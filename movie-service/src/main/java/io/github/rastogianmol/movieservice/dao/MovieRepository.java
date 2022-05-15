package io.github.rastogianmol.movieservice.dao;

import io.github.rastogianmol.movieservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<List<Movie>> findByRating(int rating);

    @Query(value = "select * from movie where YEAR(year)= ?", nativeQuery = true)
    Optional<List<Movie>> findByYear(String year);
}
