package com.example.andres.movies_test.presenter;

import com.example.andres.movies_test.model.GenreResponse;

/**
 * Created by andres on 27/01/17.
 */

public interface IMainPresenter {

    void filterByDate(GenreResponse mGenreResponse);

    void filterByAsc(GenreResponse mGenreResponse);

    void filterByDesc(GenreResponse mGenreResponse);
}
