package com.example.andres.movies_test;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.andres.movies_test.adapter.GenreAdapter;
import com.example.andres.movies_test.debug.MovieDetailActivity;
import com.example.andres.movies_test.model.Genre;
import com.example.andres.movies_test.model.Movie;
import com.example.andres.movies_test.presenter.IMainPresenter;
import com.example.andres.movies_test.presenter.MainPresenter;
import com.example.andres.movies_test.view.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        IMainView {

    @BindView(R.id.rvMovie)
    RecyclerView mRvMovie;

    private GenreAdapter mGenreAdapter;

    private IMainPresenter mIMainPresenter;

    private SearchView mSearchView;

    public static void show(final SplashActivity splashActivity) {
        splashActivity.startActivity(new Intent(splashActivity, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_date:
                mIMainPresenter.filterByDate(getGenreCopy());
                return true;
            case R.id.action_filter_alphabetically_asc:
                mIMainPresenter.filterByAsc(getGenreCopy());
                return true;
            case R.id.action_filter_alphabetically_des:
                mIMainPresenter.filterByDesc(getGenreCopy());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayData(final List<Genre> genres) {
        mGenreAdapter.clear();
        mGenreAdapter.addAll(genres);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.isEmpty()) {
            displayData(getGenreCopy());
            return false;
        }
        mGenreAdapter.clear();
        mGenreAdapter.getFilter().filter(newText.toUpperCase().trim());
        return false;
    }

    private void init() {

        mIMainPresenter = new MainPresenter(this);

        mRvMovie.setLayoutManager(new LinearLayoutManager(this));
        mRvMovie.setHasFixedSize(true);
        mRvMovie.setItemAnimator(new DefaultItemAnimator());
        mRvMovie.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvMovie.setAdapter(mGenreAdapter = new GenreAdapter(this, getGenreCopy()));
    }

    private List<Genre> getGenreCopy(){
        return mIMainPresenter.get(this);
    }

    @Override
    public void gotoMovieDetail(Movie movie) {
        MovieDetailActivity.show(this, movie);
    }
}
