package io.github.rastogianmol.movieservice.facade;

import io.github.rastogianmol.movieservice.dao.MovieRepository;
import io.github.rastogianmol.movieservice.exception.DataConflictException;
import io.github.rastogianmol.movieservice.exception.InvalidDateException;
import io.github.rastogianmol.movieservice.exception.ResourceNotFoundException;
import io.github.rastogianmol.movieservice.models.Movie;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

    public void createOrUpdateMovie(Movie movie){
        try{
            var isValidDate = GenericValidator.isDate(movie.getYear(), "yyyy-MM-dd", true);
            if(!isValidDate){
                throw new InvalidDateException("Date is not valid, please use this format yyyy-MM-dd");
            }
            movieRepository.save(movie);
        } catch (DataIntegrityViolationException ex){
            throw new DataConflictException(movie.getName() + " already exist");
        }
    }
}
