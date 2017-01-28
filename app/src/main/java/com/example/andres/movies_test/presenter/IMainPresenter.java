package com.example.andres.movies_test.presenter;

import android.content.Context;

import com.example.andres.movies_test.model.Genre;

import java.util.List;

/**
 * Created by andres on 27/01/17.
 */

public interface IMainPresenter {

    void filterByDate(final List<Genre> genres);

    void filterByAsc(final List<Genre> genres);

    void filterByDesc(final List<Genre> genres);

    List<Genre> get(Context context);
}
