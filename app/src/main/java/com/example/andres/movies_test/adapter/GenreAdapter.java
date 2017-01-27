package com.example.andres.movies_test.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andres.movies_test.R;
import com.example.andres.movies_test.model.Genre;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andres on 27/01/17.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private List<Genre> mGenres;

    public GenreAdapter(List<Genre> mGenres) {
        this.mGenres = mGenres;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Genre genre = mGenres.get(position);
        holder.mTvTitle.setText(genre.getName());
        holder.mRvMovies.setAdapter(new MovieAdapter(genre.getMovies()));
    }

    @Override
    public int getItemCount() {
        return mGenres == null?0:mGenres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView mTvTitle;

        @BindView(R.id.rv_movies)
        RecyclerView mRvMovies;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view->{
                if(mRvMovies.getVisibility() == View.GONE) {
                    mTvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(
                            view.getContext(), R.drawable.ic_keyboard_arrow_up_24px), null);
                    mRvMovies.setVisibility(View.VISIBLE);
                }else {
                    mRvMovies.setVisibility(View.GONE);
                    mTvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(
                            view.getContext(), R.drawable.ic_keyboard_arrow_down_24px), null);
                }
            });
            init();
        }

        private void init(){
            mRvMovies.setLayoutManager(new LinearLayoutManager(mRvMovies.getContext()));
            mRvMovies.setHasFixedSize(true);
            mRvMovies.setItemAnimator(new DefaultItemAnimator());
            mRvMovies.addItemDecoration(new DividerItemDecoration(mRvMovies.getContext(),
                    DividerItemDecoration.VERTICAL));

        }
    }
}
