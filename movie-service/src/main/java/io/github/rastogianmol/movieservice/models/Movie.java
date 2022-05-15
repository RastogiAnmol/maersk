package io.github.rastogianmol.movieservice.models;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private String name;
    private String year;
    private int rating;

    public Movie() {
    }
    public Movie(String name, String year, int rating) {
        this.name = name;
        this.year = year;
        this.rating = rating;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
