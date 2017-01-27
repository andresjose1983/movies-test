package com.example.andres.movies_test.interactor;

import com.example.andres.movies_test.model.MovieResponse;
import com.example.andres.movies_test.presenter.ISplashPresenter;
import com.example.andres.movies_test.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andres on 27/01/17.
 */

public class MovieInteractor {

    private ISplashPresenter mISplashPresenter;

    public MovieInteractor(ISplashPresenter mISplashPresenter) {
        this.mISplashPresenter = mISplashPresenter;
    }

    /**
     * Call service in order to movies by genreId
     * @param genreId
     */
    public void getMoviesByGenre(final String genreId){
        RestClient.getMoviesByGenre(genreId).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.body() != null)
                    mISplashPresenter.addMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mISplashPresenter.showError(t.getMessage());
            }
        });
    }
}
