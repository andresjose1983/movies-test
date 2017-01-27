package com.example.andres.movies_test.interactor;

import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.service.RestClient;

import java.util.List;

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

        RestClient.getGenres().enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {

            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

            }
        });
    }
}
