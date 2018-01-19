package com.alifyz.popularmovies.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by alify on 1/15/2018.
 */

public class MoviesContentProvider extends ContentProvider {

    private MoviesDbHelper moviesDbHelper;

    public static final int DIRECTORY = 100;
    public static final int ITEM = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES, DIRECTORY);
        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_ID, ITEM);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        moviesDbHelper = new MoviesDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final SQLiteDatabase database = moviesDbHelper.getWritableDatabase();
        Uri returnedUri = null;

        int matcher = sUriMatcher.match(uri);
        switch (matcher) {
            case DIRECTORY:
                long id = database.insert(MoviesContract.MoviesEntry.TABLE_NAME, null,contentValues);
                if(id > 0) {
                    returnedUri = ContentUris.withAppendedId(MoviesContract.BASE_CONTENT_URI, id);
                } else {
                   throw new android.database.sqlite.SQLiteException("Error inserting the movie");
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        if(returnedUri != null) {
            Toast.makeText(getContext(), returnedUri.toString(), Toast.LENGTH_LONG).show();
        }
        return returnedUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
