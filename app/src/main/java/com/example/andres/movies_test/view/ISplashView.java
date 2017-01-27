package com.example.andres.movies_test.view;

import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.model.MovieResponse;

/**
 * Created by andres on 27/01/17.
 */

public interface ISplashView {

    void goToNextView(final GenreResponse genreResponse, final MovieResponse movieResponse);

    void showError(final String error);

}
