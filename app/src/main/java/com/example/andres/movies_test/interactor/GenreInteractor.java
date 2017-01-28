package com.example.andres.movies_test.interactor;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.andres.movies_test.R;
import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.presenter.ISplashPresenter;
import com.example.andres.movies_test.service.RestClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andres on 27/01/17.
 */

public class GenreInteractor {

    private ISplashPresenter mISplashPresenter;

    public GenreInteractor(){

    }

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

    public List<Genre> get(final Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.app_name), Context.MODE_PRIVATE);
        return new Gson().fromJson(sharedPref.getString(
                context.getString(R.string.genre_object), null), GenreResponse.class).getGenres();
    }
}
