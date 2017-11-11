package com.alifyz.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Alifyz on 11/10/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public List<News> loadInBackground() {
        return null;
    }
}
