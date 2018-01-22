package com.alifyz.popularmovies;


import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.alifyz.popularmovies.Database.MoviesContract;

import com.alifyz.popularmovies.RecyclerView.MoviesViewCursorAdapter;
import com.alifyz.popularmovies.Utils.MoviesFavoriteLoader;
import com.alifyz.popularmovies.Utils.NetworkUtils;

public class MoviesFavoritesHomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, MoviesViewCursorAdapter.clickListener {

    private static final String TAG = "FavoritesHomeActivity";
    private Cursor mData;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private final int LOADER_FAV_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_favorites_home);
        setTitle(R.string.my_favorites_title);


        if(NetworkUtils.isInternetOn(this)) {
            getLoaderManager().initLoader(LOADER_FAV_ID, null, this).forceLoad();
            mProgressBar = (ProgressBar) findViewById(R.id.pbProcessing_fav);
            GridLayoutManager mLayout = new GridLayoutManager(MoviesFavoritesHomeActivity.this, 2);

            mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_fav);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayout);
            mProgressBar.setVisibility(View.VISIBLE);

        } else {
            setContentView(R.layout.activity_no_internet);
        }


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        if(id == LOADER_FAV_ID) {
            return new MoviesFavoriteLoader(this);
        } else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        mProgressBar.setVisibility(View.GONE);
        mData = cursor;

        MoviesViewCursorAdapter mMoviesViewCursorAdapter = new MoviesViewCursorAdapter(this, mData, this);
        mRecyclerView.setAdapter(mMoviesViewCursorAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mData = null;
    }


    @Override
    public void onListItemClick(int clickedItem) {
        if(mData != null) {
            mData.moveToPosition(clickedItem);
        }

        int posterIndex = mData.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_IMAGE);
        int titleIndex = mData.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_TITLE);
        int yearIndex = mData.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE);
        int ratingsIndex = mData.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_RATING);
        int durationIndex = mData.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_DURATION);
        int descriptionIndex = mData.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_DESCRIPTION);
        int trailerIndex = mData.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_TRAILER);

        String Poster = mData.getString(posterIndex);
        String Title = mData.getString(titleIndex);
        String Year = mData.getString(yearIndex);
        String Ratings = mData.getString(ratingsIndex);
        String Duration = mData.getString(durationIndex);
        String Description = mData.getString(descriptionIndex);
        String Trailer = mData.getString(trailerIndex);

        Intent favoritesIntent = new Intent(this, MoviesFavoritesDetailsActivity.class);

        favoritesIntent.putExtra("Poster", Poster);
        favoritesIntent.putExtra("Title", Title);
        favoritesIntent.putExtra("Year", Year);
        favoritesIntent.putExtra("Ratings", Ratings);
        favoritesIntent.putExtra("Duration", Duration);
        favoritesIntent.putExtra("Description", Description);
        favoritesIntent.putExtra("Trailer", Trailer);

        startActivity(favoritesIntent);
    }


}
