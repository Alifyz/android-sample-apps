package com.alifyz.popularmovies;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Network;
import android.os.Bundle;
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
    private final int LOADER_ID = 0;
    private final int LOADER_ID_POPULAR = 1;

    private ProgressBar mLoadingBar;
    private List<MoviesObject> mMovieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));

        if (NetworkUtils.isInternetOn(this)) {
            getLoaderManager().initLoader(LOADER_ID_POPULAR, null, this).forceLoad();

            GridLayoutManager mLayout = new GridLayoutManager(MoviesHomeActivity.this, 2);

            mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayout);

            mLoadingBar = (ProgressBar) findViewById(R.id.pb_loading_bar);
            mLoadingBar.setVisibility(View.VISIBLE);

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
                    getLoaderManager().restartLoader(LOADER_ID_POPULAR, null, this).forceLoad();
                } else {
                    setContentView(R.layout.activity_no_internet);
                }
                return true;
            case R.id.top_rated_movies:
                if (NetworkUtils.isInternetOn(this)) {
                    getLoaderManager().restartLoader(LOADER_ID, null, this).forceLoad();
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

        if (id == LOADER_ID_POPULAR) {
            return new MoviesLoader(this, NetworkUtils.getPopularMoviesUrl());
        } else {
            return new MoviesLoader(this, NetworkUtils.getMostRatedMoviesUrl());
        }
    }

    @Override
    public void onLoadFinished(Loader<List<MoviesObject>> loader, List<MoviesObject> results) {
        mLoadingBar.setVisibility(View.GONE);
        mMovieInfo = results;
        MoviesViewAdapter mMoviesViewAdapter = new MoviesViewAdapter(getApplicationContext(), results, this);
        mRecyclerView.setAdapter(mMoviesViewAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<MoviesObject>> loader) {
        loader.isReset();
    }

    @Override
    public void onListItemClick(int clickedItem) {
        MoviesObject currentMovie = mMovieInfo.get(clickedItem);
        Intent movieDetails = new Intent(MoviesHomeActivity.this, MoviesDetailsActivity.class);
        movieDetails.putExtra("Title", currentMovie.getmMoviesTitle());
        movieDetails.putExtra("Description", currentMovie.getmMoviesDescription());
        movieDetails.putExtra("Year", currentMovie.getmReleaseDate());
        movieDetails.putExtra("PosterImage", currentMovie.getmMovieImage());
        movieDetails.putExtra("Ratings", currentMovie.getmMovieRatings());
        movieDetails.putExtra("Id", currentMovie.getmMovieId());
        startActivity(movieDetails);
    }
}
