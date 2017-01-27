package com.example.andres.movies_test.interactor;

import com.example.andres.movies_test.model.Movie;
import com.example.andres.movies_test.service.RestClient;

import java.util.List;

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
        RestClient.getMoviesByGenre(genreId).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
    }
}
