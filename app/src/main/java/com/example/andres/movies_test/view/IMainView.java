package com.example.andres.movies_test.view;

import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.Movie;

import java.util.List;

/**
 * Created by andres on 27/01/17.
 */

public interface IMainView {

    void displayData(final List<Genre> genres);

    void gotoMovieDetail(final Movie movie);

}
