package io.github.rastogianmol.movieservice.controller;

import io.github.rastogianmol.movieservice.dao.MovieRepository;
import io.github.rastogianmol.movieservice.facade.MovieFacade;
import io.github.rastogianmol.movieservice.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieFacade movieFacade;

    @GetMapping()
    public List<Movie> getMovies(){
        return movieFacade.findAllMovies();
    }

    @GetMapping("/{rating}/ratings")
    public List<Movie> getMovies(@PathVariable("rating") int rating){
        return movieFacade.findMoviesByRating(rating);
    }

    @GetMapping("/{year}/year")
    public List<Movie> getMovies(@PathVariable("year") String year){
        return movieFacade.findMoviesByYear(year);
    }

    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<String> createMovie(@RequestBody Movie movie){
            movieFacade.createMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body( movie.getName() + " is created");
    }
}
