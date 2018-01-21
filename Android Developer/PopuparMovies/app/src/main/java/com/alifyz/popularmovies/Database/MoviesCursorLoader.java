package com.alifyz.popularmovies.Database;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

/**
 * Created by alify on 1/21/2018.
 */

public class MoviesCursorLoader extends CursorLoader {

    public MoviesCursorLoader(Context context) {
        super(context);
    }

    public MoviesCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }
}
