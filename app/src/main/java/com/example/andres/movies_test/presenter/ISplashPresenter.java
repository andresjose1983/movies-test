package com.example.andres.movies_test.presenter;

import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.model.Movie;
import com.example.andres.movies_test.model.MovieResponse;

import java.util.List;

/**
 * Created by andres on 27/01/17.
 */

public interface ISplashPresenter {

    void getGenres();

    void setGenres(final GenreResponse genreResponse);

    void showError(final String error);

    void addMovies(List<Movie> movies);
}
