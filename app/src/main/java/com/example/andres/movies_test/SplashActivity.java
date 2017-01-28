package com.example.andres.movies_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.andres.movies_test.intent.services.MovieServices;
import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.presenter.ISplashPresenter;
import com.example.andres.movies_test.presenter.SplashPresenter;
import com.example.andres.movies_test.view.ISplashView;
import com.google.gson.Gson;

public class SplashActivity extends AppCompatActivity implements ISplashView{

    private ISplashPresenter mISplashPresenter;

    public static final String INTENT_FILTER =
            "com.example.andres.movies_test.intent.INTENT_FILTER";

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            GenreResponse genreResponse = (GenreResponse) intent.getExtras()
                    .get(MovieServices.INTENT_DATA_GENRES);

            SharedPreferences sharedPref = context.getSharedPreferences(
                    getString(R.string.app_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.genre_object), new Gson().toJson(genreResponse));
            editor.commit();


            goToNextView();
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
    public void goToNextView() {
        MainActivity.show(this);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
