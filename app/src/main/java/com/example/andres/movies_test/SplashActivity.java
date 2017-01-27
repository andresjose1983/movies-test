package com.example.andres.movies_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.andres.movies_test.model.MovieResponse;
import com.example.andres.movies_test.presenter.ISplashPresenter;
import com.example.andres.movies_test.presenter.SplashPresenter;
import com.example.andres.movies_test.view.ISplashView;

public class SplashActivity extends AppCompatActivity implements ISplashView{

    private ISplashPresenter mISplashPresenter;

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
    }

    @Override
    public void goToNextView(final MovieResponse movieResponse) {
        MainActivity.show(this, movieResponse);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
