package io.github.rastogianmol.movieservice.dao;

import io.github.rastogianmol.movieservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByRating(int rating);

    List<Movie> findByYear(String year);
}
