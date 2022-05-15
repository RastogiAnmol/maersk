package io.github.rastogianmol.movieservice.dao;

import io.github.rastogianmol.movieservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByRating(int rating);

    @Query(value = "select * from movie where YEAR(year)= ?", nativeQuery = true)
    List<Movie> findByYear(String year);
}
