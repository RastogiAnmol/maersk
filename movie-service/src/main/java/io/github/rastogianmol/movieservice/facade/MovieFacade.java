package io.github.rastogianmol.movieservice.facade;

import io.github.rastogianmol.movieservice.dao.MovieRepository;
import io.github.rastogianmol.movieservice.exception.DataConflictException;
import io.github.rastogianmol.movieservice.exception.ResourceNotFoundException;
import io.github.rastogianmol.movieservice.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MovieFacade {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> findMoviesByRating(int rating) throws ResourceNotFoundException {
        return movieRepository.findByRating(rating)
                .filter(lst -> !lst.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with rating " + rating));
    }

    public List<Movie> findMoviesByYear(String year) throws ResourceNotFoundException {
        return movieRepository.findByYear(year)
                .filter(lst -> !lst.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for year " + year));
    }

    public void createMovie(Movie movie){
        try{
            movieRepository.save(movie);
        } catch (DataIntegrityViolationException ex){
            throw new DataConflictException(movie.getName() + " already exist");
        }
    }

    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
