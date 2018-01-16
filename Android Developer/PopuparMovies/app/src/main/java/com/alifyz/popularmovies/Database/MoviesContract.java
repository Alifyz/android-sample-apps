package com.alifyz.popularmovies.Database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alify on 1/15/2018.
 */

public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "com.alifyz.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = Uri.parse("content://"+ CONTENT_AUTHORITY + "/movies");


    private MoviesContract() {}

    public class MoviesEntry implements BaseColumns {
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
