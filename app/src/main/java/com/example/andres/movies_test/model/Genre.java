package com.example.andres.movies_test.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andres on 27/01/17.
 */

public class Genre implements Serializable, Comparable<Genre>{

    private int id;
    private String name;
    private List<Movie> movies;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get genre id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Get genre name
     * @return
     */
    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int compareTo(Genre genre) {
        return this.name.compareToIgnoreCase(genre.getName());
    }
}
