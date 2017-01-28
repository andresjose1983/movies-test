package com.example.andres.movies_test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andres.movies_test.BuildConfig;
import com.example.andres.movies_test.R;
import com.example.andres.movies_test.model.Movie;
import com.example.andres.movies_test.view.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andres on 27/01/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> mMovies;
    private IMainView mIMainView;

    public MovieAdapter(IMainView mIMainView, List<Movie> mMovies) {
        this.mMovies = mMovies;
        this.mIMainView = mIMainView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = mMovies.get(position);
        holder.mTvTitle.setText(movie.getTitle());
        holder.mTvDate.setText(movie.getDate());
        holder.mRbMovie.setRating(movie.getVote());
        Glide.with(holder.mIvMovie.getContext()).load(BuildConfig.URL_IMAGE + movie.getBackdropPath())
                .crossFade(500).into(holder.mIvMovie);
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie)
        ImageView mIvMovie;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.rb_movie)
        RatingBar mRbMovie;
        @BindView(R.id.tv_date)
        TextView mTvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> mIMainView.gotoMovieDetail(
                    mMovies.get(getLayoutPosition())));
        }
    }

}
