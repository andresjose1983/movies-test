package com.example.andres.movies_test.interactor;

import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andres on 27/01/17.
 */

public class GenreInteractor {

    /**
     * Call service in order to get the genres
     */
    public void getGenres() {

        RestClient.getGenres().enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {

            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {

            }
        });
    }
}
