package com.example.andres.movies_test.debug;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andres.movies_test.R;
import com.example.andres.movies_test.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String INTENT_DATA =
            "com.example.andres.movies_test.data.INTENT_DATA";

    public static void show(final Context context, final Movie movie){
        context.startActivity(new Intent(context, MovieDetailActivity.class)
        .putExtra(INTENT_DATA, movie));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }
}
