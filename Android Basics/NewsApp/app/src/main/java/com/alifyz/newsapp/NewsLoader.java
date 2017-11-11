package com.alifyz.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Alifyz on 11/10/2017.
 */

public class NewsLoader extends AsyncTaskLoader<News> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public News loadInBackground() {
        return null;
    }
}
