package com.alifyz.popularmovies.Utils;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.alifyz.popularmovies.Database.MoviesContract.MoviesEntry;

/**
 * Created by alify on 1/21/2018.
 */
public  class MoviesFavoriteLoader extends AsyncTaskLoader<Cursor> {

    private final String TAG = "Favorites Loader";

    public MoviesFavoriteLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {

        String[] projection =  {
                MoviesEntry.COLUMN_TITLE,
                MoviesEntry.COLUMN_RELEASE_DATE,
                MoviesEntry.COLUMN_DURATION,
                MoviesEntry.COLUMN_DESCRIPTION,
                MoviesEntry.COLUMN_TRAILER,
                MoviesEntry.COLUMN_IMAGE,
                MoviesEntry.COLUMN_RATING
        };

        try {
            return getContext().getContentResolver().query(MoviesEntry.CONTENT_MOVIES,
                    projection, null, null, null);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }

    }


}
