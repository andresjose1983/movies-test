package com.example.andres.movies_test.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andres on 27/01/17.
 */

public class GenreResponse implements Serializable {

    private List<Genre> genres;

    public GenreResponse(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
