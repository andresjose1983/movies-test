package com.example.andres.movies_test.presenter;

import com.example.andres.movies_test.interactor.GenreInteractor;
import com.example.andres.movies_test.interactor.MovieInteractor;
import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.model.Movie;
import com.example.andres.movies_test.model.MovieResponse;
import com.example.andres.movies_test.view.ISplashView;

/**
 * Created by andres on 27/01/17.
 */

public class SplashPresenter implements ISplashPresenter {

    private ISplashView mISplashView;
    private GenreInteractor mGenreInteractor;
    private MovieInteractor mMovieInteractor;
    private MovieResponse mMovieResponse = new MovieResponse();

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
        for (Genre genre: genreResponse.getGenres()) {
            MovieResponse moviesByGenre = mMovieInteractor.getMoviesByGenre(String.valueOf(genre.getId()));
            if(moviesByGenre != null) {
                for (Movie movie: moviesByGenre.getResults()) {
                    movie.setGenre(genre);
                    addMovie(movie);
                }
            }
        }
        mISplashView.goToNextView(genreResponse, mMovieResponse);
    }

    @Override
    public void showError(String error) {
        mISplashView.showError(error);
    }

    @Override
    public void addMovie(final Movie movie) {
        mMovieResponse.getResults().add(movie);
    }
}
