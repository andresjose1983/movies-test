package com.example.andres.movies_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.model.MovieResponse;
import com.example.andres.movies_test.presenter.ISplashPresenter;
import com.example.andres.movies_test.presenter.SplashPresenter;
import com.example.andres.movies_test.view.ISplashView;

public class SplashActivity extends AppCompatActivity implements ISplashView{

    private ISplashPresenter mISplashPresenter;

    public static final String INTENT_FILTER =
            "com.example.andres.movies_test.intent.INTENT_FILTER";

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            goToNextView((GenreResponse) intent.getExtras().get(MovieServices.INTENT_DATA_GENRES));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        mISplashPresenter = new SplashPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mISplashPresenter.getGenres();
        registerReceiver(mBroadcastReceiver, new IntentFilter(INTENT_FILTER));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void goToNextView(final GenreResponse genreResponse) {
        MainActivity.show(this, genreResponse);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
