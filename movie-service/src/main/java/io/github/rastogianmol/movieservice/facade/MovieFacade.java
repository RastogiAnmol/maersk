package io.github.rastogianmol.movieservice.facade;

import io.github.rastogianmol.movieservice.dao.MovieRepository;
import io.github.rastogianmol.movieservice.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieFacade {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> findMoviesByRating(int rating){
        return movieRepository.findByRating(rating);
    }

    public List<Movie> findMoviesByYear(String year){
        return movieRepository.findByYear(year);
    }

    public void createMovie(Movie movie){
        movieRepository.save(movie);
    }
}
