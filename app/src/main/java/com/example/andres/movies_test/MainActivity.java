package com.example.andres.movies_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.model.MovieResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvMovie)
    RecyclerView recyclerView;

    private static final String INTENT_DATA_MOVIES =
            "com.example.andres.movies_test.data.INTENT_DATA_MOVIES";

    private static final String INTENT_DATA_GENRES =
            "com.example.andres.movies_test.data.INTENT_DATA_GENRES";

    public static void show(final SplashActivity splashActivity, final GenreResponse genreResponse,
                            final MovieResponse movieResponse){
        splashActivity.startActivity(new Intent(splashActivity, MainActivity.class)
                .putExtra(INTENT_DATA_GENRES, genreResponse)
                .putExtra(INTENT_DATA_MOVIES, movieResponse));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init(){

    }
}
