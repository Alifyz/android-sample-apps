package com.alifyz.popularmovies.Utils;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Alifyz on 12/6/2017.
 */

public class MoviesLoader extends AsyncTaskLoader<List<MoviesObject>> {

    private String urlAddress;

    public MoviesLoader(Context context, String getUrl) {
        super(context);
        urlAddress = getUrl;

    }

    @Override
    public List<MoviesObject> loadInBackground() {

        String rawJson = NetworkUtils.makeHttpRequest(urlAddress);
        List<MoviesObject> movies = NetworkUtils.extractData(rawJson);

        return movies;
    }
}
