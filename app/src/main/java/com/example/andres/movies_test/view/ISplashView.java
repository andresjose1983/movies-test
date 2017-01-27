package com.example.andres.movies_test.view;

import com.example.andres.movies_test.model.GenreResponse;

/**
 * Created by andres on 27/01/17.
 */

public interface ISplashView {

    void goToNextView(final GenreResponse genreResponse);

    void showError(final String error);

}
