package com.example.andres.movies_test;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andres.movies_test.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String INTENT_DATA = "com.example.andres.movies_test.data.INTENT_DATA";
    @BindView(R.id.tb_movie)
    Toolbar mTbPhoto;
    @BindView(R.id.iv_movie)
    ImageView mIvMovie;
    @BindView(R.id.iv_movie_icon)
    ImageView mIvMovieIcon;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_overview)
    TextView mTvOverview;
    @BindView(R.id.rb_movie)
    RatingBar mRbMovie;

    Movie movie;


    public static void show(final MainActivity mainActivity, final RatingBar ratingBar,
                            TextView title, TextView tvDate, final ImageView ivMovie,
                            final Movie movie){
        Intent intent = new Intent(mainActivity, MovieDetailActivity.class).putExtra(INTENT_DATA, movie);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            Pair<View, String> p1 = Pair.create(ivMovie, "movie_picture");
            Pair<View, String> p2 = Pair.create(tvDate, "date");
            Pair<View, String> p3 = Pair.create(title, "title");
            Pair<View, String> p4 = Pair.create(ratingBar, "rating");

            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(mainActivity, p1, p2, p3, p4);
            mainActivity.startActivity(intent, options.toBundle());
        }else
            mainActivity.startActivity(intent);
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
        Glide.with(this).load(BuildConfig.URL_IMAGE_POST + movie.getBackdropPath())
                .centerCrop().crossFade(500).into(mIvMovieIcon);

        mTbPhoto.setTitle(movie.getTitle());
        mTvDate.setText(movie.getDate());
        mTvTitle.setText(movie.getOriginalTitle());
        mTvOverview.setText(movie.getOverview());
        mRbMovie.setRating(movie.getVote());

        LayerDrawable stars = (LayerDrawable) mRbMovie.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(mRbMovie.getContext(),
                R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(ContextCompat.getColor(mRbMovie.getContext(),
                R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(ContextCompat.getColor(mRbMovie.getContext(),
                R.color.yellow), PorterDuff.Mode.SRC_ATOP);


        setSupportActionBar(mTbPhoto);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
