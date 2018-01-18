package com.alifyz.popularmovies.Database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alify on 1/15/2018.
 */

public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "com.alifyz.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIES = "movies";
    public static final String PATH_MOVIES_ID = "movies/#";

    private MoviesContract() {}

    public static class MoviesEntry implements BaseColumns {

        public static final Uri CONTENT_MOVIES = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();
        public static final Uri CONTENT_MOVIES_ID = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES+"/#").build();

        public static final String TABLE_NAME = "movies";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "date";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_TRAILER = "trailer";
        public static final String COLUMN_COMMENTS = "comments";
    }
}
