package com.alifyz.popularmovies;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.alifyz.popularmovies.Database.MoviesContract;
import com.alifyz.popularmovies.RecyclerView.MoviesViewAdapter;
import com.alifyz.popularmovies.RecyclerView.MoviesViewCursorAdapter;
import com.alifyz.popularmovies.Utils.MoviesFavoriteLoader;

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

        getLoaderManager().initLoader(LOADER_FAV_ID, null, this).forceLoad();

        mProgressBar = (ProgressBar) findViewById(R.id.pbProcessing_fav);
        GridLayoutManager mLayout = new GridLayoutManager(MoviesFavoritesHomeActivity.this, 2);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_fav);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayout);

        mProgressBar.setVisibility(View.VISIBLE);

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

    }


}
