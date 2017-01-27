package com.example.andres.movies_test.presenter;

import com.example.andres.movies_test.interactor.GenreInteractor;
import com.example.andres.movies_test.interactor.MovieInteractor;
import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.model.Movie;
import com.example.andres.movies_test.model.MovieResponse;
import com.example.andres.movies_test.view.ISplashView;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by andres on 27/01/17.
 */

public class SplashPresenter implements ISplashPresenter {

    private ISplashView mISplashView;
    private GenreInteractor mGenreInteractor;
    private MovieInteractor mMovieInteractor;

    public SplashPresenter(ISplashView mISplashView) {
        this.mISplashView = mISplashView;
        mGenreInteractor = new GenreInteractor(this);
        mMovieInteractor = new MovieInteractor(this);
    }

    @Override
    public void getGenres() {
        mGenreInteractor.getGenres();
    }

    @Override
    public void setGenres(GenreResponse genreResponse) {
        //Order
        Collections.sort(genreResponse.getGenres());
        for (Genre genre: genreResponse.getGenres()) {
            MovieResponse moviesByGenre = mMovieInteractor.getMoviesByGenre(String.valueOf(genre.getId()));
            //Order
            Collections.sort(moviesByGenre.getResults(), (m, m1)->{
                if(m.getTitle().compareToIgnoreCase(m1.getTitle()) == 0){
                    if(m.getOriginalTitle().compareToIgnoreCase(m1.getOriginalTitle()) == 0){
                        return m.getOverview().compareToIgnoreCase(m1.getOverview());
                    }else
                        return m.getOriginalTitle().compareToIgnoreCase(m1.getOriginalLanguage());

                }else
                    return m.getTitle().compareToIgnoreCase(m1.getTitle());
            });
            genre.setMovies(moviesByGenre.getResults());
        }
        mISplashView.goToNextView(genreResponse);
    }

    @Override
    public void showError(String error) {
        mISplashView.showError(error);
    }
}
