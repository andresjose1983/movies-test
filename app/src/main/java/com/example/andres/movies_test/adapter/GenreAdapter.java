package com.example.andres.movies_test.adapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andres.movies_test.R;
import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.Movie;
import com.example.andres.movies_test.view.IMainView;

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
    private IMainView mIMainView;

    public GenreAdapter(IMainView mIMainView, List<Genre> mGenres) {
        this.mGenres = mGenres;
        this.mGenresFilter = new ArrayList<>(this.mGenres);
        this.mIMainView = mIMainView;
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
        holder.mRvMovies.setAdapter(new MovieAdapter(mIMainView, genre.getMovies()));
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

        @BindView(R.id.iv_direction)
        ImageView mIvDirection;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if (mRvMovies.getVisibility() == View.GONE) {
                    mIvDirection.setImageResource(R.drawable.ic_keyboard_arrow_up_24px);
                    mRvMovies.setVisibility(View.VISIBLE);
                } else {
                    mIvDirection.setImageResource(R.drawable.ic_keyboard_arrow_down_24px);
                    mRvMovies.setVisibility(View.GONE);
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
                    List<Movie> movies = new ArrayList<>();
                    for (Movie movie : genre.getMovies()) {
                        try {
                            float rating = Float.parseFloat((String) constraint);
                            if (movie.getVote() >= rating)
                                movies.add(movie);
                        } catch (NumberFormatException e) {
                            boolean verify = movie.getTitle().toUpperCase().contains(constraint)
                                    || movie.getOverview().toUpperCase().contains(constraint)
                                    || movie.getOriginalTitle().toUpperCase().contains(constraint);
                            if (verify)
                                movies.add(movie);
                        }
                    }
                    if (!movies.isEmpty()) {
                        genre.getMovies().clear();
                        genre.getMovies().addAll(movies);
                        mFiltersGenres.add(genre);
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

    public void addAll(List<Genre> genres) {
        mGenres = genres;
        notifyDataSetChanged();
    }

}
