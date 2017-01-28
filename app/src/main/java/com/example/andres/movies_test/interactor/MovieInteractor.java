package com.example.andres.movies_test.interactor;

import com.example.andres.movies_test.model.MovieResponse;
import com.example.andres.movies_test.presenter.ISplashPresenter;
import com.example.andres.movies_test.service.RestClient;

import java.io.IOException;

/**
 * Created by andres on 27/01/17.
 */

public class MovieInteractor {

    /**
     * Call service in order to movies by genreId
     * @param genreId
     */
    public MovieResponse getMoviesByGenre(final String genreId){
        try {
            return RestClient.getMoviesByGenre(genreId).execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}
