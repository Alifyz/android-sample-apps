package com.alifyz.popularmovies;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.alifyz.popularmovies.RecyclerView.MoviesViewAdapter;
import com.alifyz.popularmovies.Utils.MoviesLoader;
import com.alifyz.popularmovies.Utils.MoviesObject;

import java.util.List;

public class MoviesHomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MoviesObject>>, MoviesViewAdapter.clickListener {

    private RecyclerView mRecyclerView;

    private final int LOADER_ID = 0;
    private final String MOVIE_DB_POPULAR = "http://api.themoviedb.org/3/movie/popular?api_key=62d5d37de3d19b41fbd9f819e9ef5513";
    private final String MOVIE_DB_TOP_RATED = "http://api.themoviedb.org/3/movie/top_rated?api_key=62d5d37de3d19b41fbd9f819e9ef5513";

    private Boolean isTopRatedActive = false;
    private ProgressBar mLoadingBar;
    private List<MoviesObject> mMovieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();

        GridLayoutManager mLayout = new GridLayoutManager(MoviesHomeActivity.this, 2);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayout);

        mLoadingBar = (ProgressBar) findViewById(R.id.pb_loading_bar);
        mLoadingBar.setVisibility(View.VISIBLE);

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
                isTopRatedActive = false;
                getLoaderManager().restartLoader(LOADER_ID, null, this).forceLoad();
                return true;
            case R.id.top_rated_movies:
                isTopRatedActive = true;
                getLoaderManager().restartLoader(LOADER_ID, null, this).forceLoad();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Loader<List<MoviesObject>> onCreateLoader(int i, Bundle bundle) {
        if (isTopRatedActive) {
            return new MoviesLoader(this, MOVIE_DB_TOP_RATED);
        } else {
            return new MoviesLoader(this, MOVIE_DB_POPULAR);
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
        startActivity(movieDetails);
    }
}
