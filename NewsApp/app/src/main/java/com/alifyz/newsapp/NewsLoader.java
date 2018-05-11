package com.alifyz.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Alifyz on 11/10/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public static String rawJSON = "";

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public List<News> loadInBackground() {

        URL parsedURL = NewsUtils.parseURL();
        try {
            rawJSON = NewsUtils.makeHttpRequest(parsedURL);
        } catch (IOException e) {
            return null;
        }
        List<News> news = NewsUtils.extractBooks(rawJSON);
        return news;
    }
}
