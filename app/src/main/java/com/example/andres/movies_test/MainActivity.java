package com.example.andres.movies_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andres.movies_test.model.Movie;
import com.example.andres.movies_test.model.MovieResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String INTENT_DATA_MOVIES =
            "com.example.andres.movies_test.data.INTENT_DATA_MOVIES";

    public static void show(final SplashActivity splashActivity, final MovieResponse movieResponse){
        splashActivity.startActivity(new Intent(splashActivity, MainActivity.class)
                .putExtra(INTENT_DATA_MOVIES, movieResponse));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
