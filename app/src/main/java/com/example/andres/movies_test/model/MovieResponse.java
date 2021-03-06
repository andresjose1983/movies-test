package com.example.andres.movies_test.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres on 27/01/17.
 */

public class MovieResponse implements Serializable {

    private List<Movie> results = new ArrayList<>();

    public MovieResponse() {
    }

    public MovieResponse(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }
}
