package com.alifyz.csbooking;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Alifyz on 11/7/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>>{

    public BookLoader(Context context) {
        super(context);
    }

    @Override
    public List<Book> loadInBackground() {
        return null;
    }
}
