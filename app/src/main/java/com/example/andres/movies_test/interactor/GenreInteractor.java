package com.example.andres.movies_test.interactor;

import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.presenter.ISplashPresenter;
import com.example.andres.movies_test.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andres on 27/01/17.
 */

public class GenreInteractor {

    private ISplashPresenter mISplashPresenter;

    public GenreInteractor(ISplashPresenter mISplashPresenter) {
        this.mISplashPresenter = mISplashPresenter;
    }

    /**
     * Call service in order to get the genres
     */
    public void getGenres() {

        RestClient.getGenres().enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                mISplashPresenter.setGenres(response.body());
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                mISplashPresenter.showError(t.getMessage());
            }
        });
    }
}
