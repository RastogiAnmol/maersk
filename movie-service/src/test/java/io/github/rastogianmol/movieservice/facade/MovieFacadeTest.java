package io.github.rastogianmol.movieservice.facade;

import io.github.rastogianmol.movieservice.dao.MovieRepository;
import io.github.rastogianmol.movieservice.models.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
public class MovieFacadeTest {

    @Mock
    private MovieRepository mockMovieRepository;

    @Autowired
    private MovieRepository movieRepository;

    private List<Movie> lstMovies = new ArrayList<>();

    @Before
    public void setUp(){
        lstMovies.add(new Movie("Batman", "2000", 5));
        lstMovies.add(new Movie("Superman", "2001", 4));
        lstMovies.add(new Movie("Spiderman", "2002", 3));
        lstMovies.add(new Movie("Aquaman", "2003", 2));
        lstMovies.add(new Movie("Incredibles", "2004", 1));
    }

    @Test
    public void shouldReturnAllMovies(){
        when(mockMovieRepository.findAll()).thenReturn(lstMovies);
        var allMovies = mockMovieRepository.findAll();
        Assertions.assertEquals(5, allMovies.size());
    }

    @Test
    public void shouldReturnMoviesByRating(){
        List<Movie> lstMoviesByRating = new ArrayList<>();
        lstMoviesByRating.add(new Movie("Superman", "2001", 4));
        when(mockMovieRepository.findByRating(anyInt())).thenReturn(Optional.of(lstMoviesByRating));
        var moviesByRating = mockMovieRepository.findByRating(4);
        Assertions.assertEquals(1, moviesByRating.get().size());
    }

    @Test
    public void shouldReturnMoviesByYear(){
        List<Movie> lstMoviesByYear = new ArrayList<>();
        lstMoviesByYear.add(new Movie("Batman", "2000", 5));
        lstMoviesByYear.add(new Movie("Superman", "2000", 5));

        when(mockMovieRepository.findByYear(anyString())).thenReturn(Optional.of(lstMoviesByYear));
        var moviesByYear = mockMovieRepository.findByYear("2000");
        Assertions.assertEquals(2, moviesByYear.get().size());
    }

}
