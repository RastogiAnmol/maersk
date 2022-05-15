package io.github.rastogianmol.movieservice.controller;

import io.github.rastogianmol.movieservice.facade.MovieFacade;
import io.github.rastogianmol.movieservice.models.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private List<Movie> lstMovies = new ArrayList<>();

    @MockBean
    private MovieFacade movieFacade;

    @Before
    public void setUp(){
        lstMovies.add(new Movie("Batman", "2000", 5));
        lstMovies.add(new Movie("Superman", "2001", 4));
        lstMovies.add(new Movie("Spiderman", "2002", 3));
        lstMovies.add(new Movie("Aquaman", "2003", 2));
        lstMovies.add(new Movie("Incredibles", "2004", 1));
    }


    @Test
    public void shouldReturnAllMovies() throws Exception {
        when(movieFacade.findAllMovies()).thenReturn(lstMovies);

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("[0]name", is("Batman")))
                .andExpect(jsonPath("[1]name", is("Superman")))
                .andExpect(jsonPath("[2]name", is("Spiderman")))
                .andExpect(jsonPath("[3]name", is("Aquaman")))
                .andExpect(jsonPath("[4]name", is("Incredibles")));
    }


    @ParameterizedTest
    @CsvSource({"Batman, 5, 2000", "Superman, 4, 2001", "Spiderman, 3, 2002", "Aquaman, 2, 2003", "Incredibles, 1, 2004"})
    public void shouldReturnMoviesByRating(String name, int rating, String year) throws Exception {
        var movieList = new ArrayList<Movie>();
        movieList.add(new Movie(name, year, rating));
        when(movieFacade.findMoviesByRating(rating)).thenReturn(movieList);
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/"+rating+"/ratings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0]name", is(name)))
                .andExpect(jsonPath("[0]year", is(year)));
    }

    @ParameterizedTest
    @CsvSource({"Batman, 5, 2000", "Superman, 4, 2001", "Spiderman, 3, 2002", "Aquaman, 2, 2003", "Incredibles, 1, 2004"})
    public void shouldReturnMoviesByYear(String name, int rating, String year) throws Exception {
        var movieList = new ArrayList<Movie>();
        movieList.add(new Movie(name, year, rating));
        when(movieFacade.findMoviesByYear(year)).thenReturn(movieList);
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/"+year+"/year"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0]name", is(name)))
                .andExpect(jsonPath("[0]year", is(year)));
    }

    @Test
    public void shouldCreateMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/movie")
                .contentType("application/json")
                        .content("{\"name\": \"A new movie\",\"year\": \"2020-01-02\",\"rating\": 5}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/movie")
                        .contentType("application/json")
                        .content("{\"id\":1,\"name\": \"A new movie\",\"year\": \"2020-01-02\",\"rating\": 5}"))
                .andExpect(status().isOk());
    }
}
