package com.example.andres.movies_test.debug;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andres.movies_test.BuildConfig;
import com.example.andres.movies_test.R;
import com.example.andres.movies_test.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String INTENT_DATA = "com.example.andres.movies_test.data.INTENT_DATA";
    @BindView(R.id.tb_movie)
    Toolbar mTbPhoto;
    @BindView(R.id.iv_movie)
    ImageView mIvMovie;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_overview)
    TextView mTvOverview;
    @BindView(R.id.rb_movie)
    RatingBar mRbMovie;

    Movie movie;


    public static void show(final Context context, final Movie movie){
        context.startActivity(new Intent(context, MovieDetailActivity.class)
        .putExtra(INTENT_DATA, movie));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){

        movie = (Movie)getIntent().getExtras().get(INTENT_DATA);

        Glide.with(this).load(BuildConfig.URL_IMAGE_POST + movie.getPosterPath())
                .centerCrop().crossFade(500).into(mIvMovie);

        mTbPhoto.setTitle(movie.getTitle());
        mTvDate.setText(movie.getDate());
        mTvTitle.setText(movie.getOriginalTitle());
        mTvOverview.setText(movie.getOverview());
        mRbMovie.setRating(movie.getVote());

        setSupportActionBar(mTbPhoto);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
