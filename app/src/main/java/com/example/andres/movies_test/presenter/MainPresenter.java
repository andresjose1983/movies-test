package com.example.andres.movies_test.presenter;

import android.util.Log;

import com.example.andres.movies_test.MainActivity;
import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.view.IMainView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by andres on 27/01/17.
 */

public class MainPresenter implements IMainPresenter {

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
    private Calendar mCalendar = Calendar.getInstance();
    private Calendar mCalendar1 = Calendar.getInstance();

    private IMainView mIMainView;

    public MainPresenter(IMainView mIMainView) {
        this.mIMainView = mIMainView;
    }

    @Override
    public void filterByDate(GenreResponse mGenreResponse) {
        for (Genre genre : mGenreResponse.getGenres()) {
            Collections.sort(genre.getMovies(), (m, m1) -> {
                try {
                    mCalendar.setTime(mSimpleDateFormat.parse(m.getDate()));
                    mCalendar1.setTime(mSimpleDateFormat.parse(m1.getDate()));
                    return mCalendar.compareTo(mCalendar1);
                } catch (ParseException e) {
                    Log.i(MainPresenter.class.getCanonicalName(), e.getLocalizedMessage());
                }
                return 0;
            });
        }
        mIMainView.displayData(mGenreResponse.getGenres());
    }

    @Override
    public void filterByAsc(GenreResponse mGenreResponse) {
        for (Genre genre : mGenreResponse.getGenres())
            Collections.sort(genre.getMovies());
        mIMainView.displayData(mGenreResponse.getGenres());
    }

    @Override
    public void filterByDesc(GenreResponse mGenreResponse) {
        for (Genre genre : mGenreResponse.getGenres())
            Collections.sort(genre.getMovies(), Collections.reverseOrder());
        mIMainView.displayData(mGenreResponse.getGenres());
    }
}
