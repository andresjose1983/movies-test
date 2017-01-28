package com.example.andres.movies_test.presenter;

import android.content.Intent;

import com.example.andres.movies_test.App;
import com.example.andres.movies_test.MovieServices;
import com.example.andres.movies_test.interactor.GenreInteractor;
import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.view.ISplashView;

/**
 * Created by andres on 27/01/17.
 */

public class SplashPresenter implements ISplashPresenter {

    private ISplashView mISplashView;
    private GenreInteractor mGenreInteractor;

    public SplashPresenter(ISplashView mISplashView) {
        this.mISplashView = mISplashView;
        mGenreInteractor = new GenreInteractor(this);
    }

    @Override
    public void getGenres() {
        mGenreInteractor.getGenres();
    }

    @Override
    public void setGenres(GenreResponse genreResponse) {
        // start services
        App context = App.getInstance();
        Intent intent = new Intent(context, MovieServices.class);
        intent.putExtra(MovieServices.INTENT_DATA_GENRES, genreResponse);
        context.startService(intent);
    }

    @Override
    public void showError(String error) {
        mISplashView.showError(error);
    }
}
