package com.alifyz.popularmovies;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Network;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.alifyz.popularmovies.Database.MoviesContract;
import com.alifyz.popularmovies.Database.MoviesDbHelper;
import com.alifyz.popularmovies.RecyclerView.MoviesViewAdapter;
import com.alifyz.popularmovies.Utils.MoviesLoader;
import com.alifyz.popularmovies.Utils.MoviesObject;
import com.alifyz.popularmovies.Utils.NetworkUtils;

import java.util.List;

public class MoviesHomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MoviesObject>>, MoviesViewAdapter.clickListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayout;

    private final int LOADER_ID = 0;
    private final int LOADER_ID_POPULAR = 1;

    private ProgressBar mLoadingBar;
    private List<MoviesObject> mMovieInfo;

    private Parcelable mLayoutPosition;
    private int mSelectedLoaderId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.app_name));
        setContentView(R.layout.activity_main);
        bindViews();

        if (NetworkUtils.isInternetOn(this) && savedInstanceState == null) {
            getLoaderManager().initLoader(LOADER_ID_POPULAR, null, this).forceLoad();
        } else if (savedInstanceState != null && NetworkUtils.isInternetOn(this)) {
            mLayoutPosition = savedInstanceState.getParcelable("position");
            getLoaderManager().restartLoader(savedInstanceState.getInt("selector"), null, this).forceLoad();
        } else {
            setContentView(R.layout.activity_no_internet);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selection = item.getItemId();
        switch (selection) {
            case R.id.popular_movies:
                if (NetworkUtils.isInternetOn(this)) {
                    bindViews();
                    mLayoutPosition = mLayout.onSaveInstanceState();
                    mSelectedLoaderId = LOADER_ID_POPULAR;
                    getLoaderManager().initLoader(LOADER_ID_POPULAR, null, this).forceLoad();
                } else {
                    setContentView(R.layout.activity_no_internet);
                }
                return true;
            case R.id.top_rated_movies:
                if (NetworkUtils.isInternetOn(this)) {
                    bindViews();
                    mLayoutPosition = mLayout.onSaveInstanceState();
                    mSelectedLoaderId = LOADER_ID;
                    getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
                } else {
                    setContentView(R.layout.activity_no_internet);
                }
                return true;
            case R.id.favorites:
                Intent openFavorites = new Intent(this, MoviesFavoritesHomeActivity.class);
                startActivity(openFavorites);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Loader<List<MoviesObject>> onCreateLoader(int id, Bundle bundle) {

        if (mLoadingBar != null) {
            mLoadingBar = (ProgressBar) findViewById(R.id.pb_loading_bar);
            mLoadingBar.setVisibility(View.VISIBLE);
        }

        switch (id) {
            case LOADER_ID_POPULAR:
                mSelectedLoaderId = LOADER_ID_POPULAR;
                return new MoviesLoader(this, NetworkUtils.getPopularMoviesUrl());
            case LOADER_ID:
                mSelectedLoaderId = LOADER_ID;
                return new MoviesLoader(this, NetworkUtils.getMostRatedMoviesUrl());
            default:
                return new MoviesLoader(this, NetworkUtils.getPopularMoviesUrl());
        }

    }

    @Override
    public void onLoadFinished(Loader<List<MoviesObject>> loader, List<MoviesObject> results) {

        if (mLoadingBar != null) {
            mLoadingBar.setVisibility(View.GONE);
        }

        mMovieInfo = results;

        if (mLayoutPosition != null) {
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mLayoutPosition);
        }

        MoviesViewAdapter mMoviesViewAdapter = new MoviesViewAdapter(getApplicationContext(), results, this);
        mRecyclerView.setAdapter(mMoviesViewAdapter);

    }

    @Override
    public void onLoaderReset(Loader<List<MoviesObject>> loader) {
        loader.isReset();
    }

    @Override
    public void onListItemClick(int clickedItem) {
        if (NetworkUtils.isInternetOn(this)) {
            MoviesObject currentMovie = mMovieInfo.get(clickedItem);
            Intent movieDetails = new Intent(MoviesHomeActivity.this, MoviesDetailsActivity.class);
            movieDetails.putExtra("Title", currentMovie.getmMoviesTitle());
            movieDetails.putExtra("Description", currentMovie.getmMoviesDescription());
            movieDetails.putExtra("Year", currentMovie.getmReleaseDate());
            movieDetails.putExtra("PosterImage", currentMovie.getmMovieImage());
            movieDetails.putExtra("Ratings", currentMovie.getmMovieRatings());
            movieDetails.putExtra("Id", currentMovie.getmMovieId());
            startActivity(movieDetails);
        } else {
            setContentView(R.layout.activity_no_internet);
        }
    }

    private void bindViews() {
        setContentView(R.layout.activity_main);
        mLayout = new GridLayoutManager(MoviesHomeActivity.this, 2);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayout);

        mLoadingBar = (ProgressBar) findViewById(R.id.pb_loading_bar);
        mLoadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("selector", mSelectedLoaderId);
        outState.putParcelable("position", mLayout.onSaveInstanceState());
    }
}
