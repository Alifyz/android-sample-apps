package com.alifyz.popularmovies.Utils;
import android.content.AsyncTaskLoader;
import android.content.Context;



/**
 * Created by alify on 1/20/2018.
 */

public class MovieDetailsLoader extends AsyncTaskLoader<MovieDetailsObject>{

    private int movieId;

    public MovieDetailsLoader(Context context, int movieId) {
        super(context);
        this.movieId = movieId;
    }

    @Override
    public MovieDetailsObject loadInBackground() {

        String[] trailer = NetworkUtils.getTrailers(String.valueOf(movieId));
        String[] comment = NetworkUtils.getComments(String.valueOf(movieId));
        String[] authors = NetworkUtils.getAuthors(String.valueOf(movieId));
        String duration = NetworkUtils.getDuration(String.valueOf(movieId));

        MovieDetailsObject result = new MovieDetailsObject(trailer, comment, authors, duration);

        return result;
    }
}
