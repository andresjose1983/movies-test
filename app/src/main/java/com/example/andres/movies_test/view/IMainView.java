package com.example.andres.movies_test.view;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.Movie;

import java.util.List;

/**
 * Created by andres on 27/01/17.
 */

public interface IMainView {

    void displayData(final List<Genre> genres);

    void gotoMovieDetail(final RatingBar ratingBar, final TextView title, final TextView tvDate, final ImageView ivMovie, final Movie movie);

}
