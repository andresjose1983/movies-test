package com.example.andres.movies_test.intent.services;

import android.app.IntentService;
import android.content.Intent;

import com.example.andres.movies_test.SplashActivity;
import com.example.andres.movies_test.interactor.MovieInteractor;
import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.model.MovieResponse;

import java.util.Collections;

/**
 * Created by andres on 27/01/17.
 */

public class MovieServices extends IntentService {

    private MovieInteractor mMovieInteractor;

    public static final String INTENT_DATA_GENRES =
            "com.example.andres.movies_test.data.INTENT_DATA_GENRES";

    @Override
    public void onCreate() {
        super.onCreate();
        mMovieInteractor = new MovieInteractor();
    }

    public MovieServices() {
        super("MovieServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GenreResponse genreResponse = (GenreResponse)intent.getExtras().get(INTENT_DATA_GENRES);
        Collections.sort(genreResponse.getGenres());

        for (Genre genre: genreResponse.getGenres()) {
            MovieResponse moviesByGenre = mMovieInteractor.getMoviesByGenre(
                    String.valueOf(genre.getId()));
            Collections.sort(moviesByGenre.getResults());
            genre.setMovies(moviesByGenre.getResults());
        }

        Intent intent1 = new Intent();
        intent1.putExtra(INTENT_DATA_GENRES, genreResponse);
        intent1.setAction(SplashActivity.INTENT_FILTER);
        sendBroadcast(intent1);

        stopSelf();
    }
}
