package com.example.andres.movies_test.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.andres.movies_test.R;
import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andres on 27/01/17.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> implements Filterable {

    private List<Genre> mGenres;
    private List<Genre> mGenresFilter = new ArrayList<>();

    public GenreAdapter(List<Genre> mGenres) {
        this.mGenres = mGenres;
        this.mGenresFilter = new ArrayList<>(this.mGenres);
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
        return mGenres == null ? 0 : mGenres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTvTitle;

        @BindView(R.id.rv_movies)
        RecyclerView mRvMovies;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if (mRvMovies.getVisibility() == View.GONE) {
                    mTvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(
                            view.getContext(), R.drawable.ic_keyboard_arrow_up_24px), null);
                    mRvMovies.setVisibility(View.VISIBLE);
                } else {
                    mRvMovies.setVisibility(View.GONE);
                    mTvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(
                            view.getContext(), R.drawable.ic_keyboard_arrow_down_24px), null);
                }
            });
            init();
        }

        private void init() {
            mRvMovies.setLayoutManager(new LinearLayoutManager(mRvMovies.getContext()));
            mRvMovies.setHasFixedSize(true);
            mRvMovies.setItemAnimator(new DefaultItemAnimator());
            mRvMovies.addItemDecoration(new DividerItemDecoration(mRvMovies.getContext(),
                    DividerItemDecoration.VERTICAL));

        }
    }

    @Override
    public Filter getFilter() {
        return new GenreFilter(this, mGenresFilter);
    }

    public void clear() {
        mGenres.clear();
        notifyDataSetChanged();
    }

    private static class GenreFilter extends Filter {

        private List<Genre> mFiltersGenres = new ArrayList<>();
        final private List<Genre> mGenres;
        final private GenreAdapter mGenreAdapter;

        public GenreFilter(GenreAdapter mGenreAdapter, List<Genre> mGenres) {
            this.mGenreAdapter = mGenreAdapter;
            this.mGenres = mGenres;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            mFiltersGenres.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                mFiltersGenres.addAll(mGenres);
            } else {
                for (final Genre genre : mGenres) {
                    try {
                        List<Movie> movies = new ArrayList<>();
                        for (Movie movie : genre.getMovies()) {
                            boolean verify = movie.getTitle().toUpperCase().contains(constraint)
                                    || movie.getOverview().toUpperCase().contains(constraint)
                                    || movie.getOriginalTitle().toUpperCase().contains(constraint);
                            if (verify)
                                movies.add(movie);
                        }
                        if(!movies.isEmpty()) {
                            genre.getMovies().clear();
                            genre.getMovies().addAll(movies);
                            mFiltersGenres.add(genre);
                        }
                    } catch (NullPointerException e) {
                        Log.e(GenreFilter.class.getCanonicalName(), e.getMessage());
                    }
                }
            }
            results.values = mFiltersGenres;
            results.count = mFiltersGenres.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mGenreAdapter.mGenres.clear();
            if (filterResults.values != null)
                mGenreAdapter.mGenres.addAll((ArrayList<Genre>) filterResults.values);
            mGenreAdapter.notifyDataSetChanged();
        }
    }

}
