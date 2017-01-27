package com.example.andres.movies_test.interactor;

import com.example.andres.movies_test.model.MovieResponse;
import com.example.andres.movies_test.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andres on 27/01/17.
 */

public class MovieInteractor {

    /**
     * Call service in order to movies by genreId
     * @param genreId
     */
    public void getMoviesByGenre(final String genreId){
        RestClient.getMoviesByGenre(genreId).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
