package io.github.rastogianmol.movieservice.controller;

import io.github.rastogianmol.movieservice.dao.MovieRepository;
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
    private MovieRepository movieRepository;

    @GetMapping()
    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    @GetMapping("/{rating}/ratings")
    public List<Movie> getMovies(@PathVariable("rating") int rating){
        return movieRepository.findByRating(rating);
    }

    @GetMapping("/{year}/year")
    public List<Movie> getMovies(@PathVariable("year") String year){
        return movieRepository.findByYear(year);
    }

    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<String> createMovie(@RequestBody Movie movie){
        try{
            movieRepository.save(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body( movie.getName() + " is created");
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT, movie.getName() + " already exist");
        }
    }
}
