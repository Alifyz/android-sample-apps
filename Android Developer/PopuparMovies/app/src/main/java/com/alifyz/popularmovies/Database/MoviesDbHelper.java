package com.alifyz.popularmovies.Database;

import com.alifyz.popularmovies.Database.MoviesContract.MoviesEntry;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by alify on 1/15/2018.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE = "CREATE TABLE " + MoviesEntry.TABLE_NAME + "(" +
                MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MoviesEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + MoviesEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, "
                + MoviesEntry.COLUMN_DURATION + " TEXT NOT NULL, "
                + MoviesEntry.COLUMN_RATING + " TEXT NOT NULL, "
                + MoviesEntry.COLUMN_IMAGE + " TEXT NOT NULL, "
                + MoviesEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + MoviesEntry.COLUMN_COMMENTS + "TEXT NOT NULL, "
                + MoviesEntry.COLUMN_TRAILER + "TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
