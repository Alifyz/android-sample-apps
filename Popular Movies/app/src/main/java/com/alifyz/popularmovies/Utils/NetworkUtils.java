package com.alifyz.popularmovies.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import com.alifyz.popularmovies.BuildConfig;
import com.alifyz.popularmovies.RecyclerView.MoviesViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alifyz on 12/5/2017.
 */

public class NetworkUtils {

    //CONSTANT -> Size of the Poster Image
    private final static String SIZE = "w185";
    private final static String TAG = "NetworkUtils";


    public static boolean isInternetOn(Context context) {
        ConnectivityManager cmManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cmManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String makeHttpRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.e(TAG, "Error while making the Http Request");
            return null;
        } catch (NullPointerException e) {
            Log.e(TAG, "Error with a null object while making the Http Request");
            return null;
        }
    }

    public static List<MoviesObject> extractData(String jsonResponse) {

        List<MoviesObject> movieList = new ArrayList<>();

        try {

            JSONObject jsonRoot = new JSONObject(jsonResponse);
            JSONArray results = jsonRoot.getJSONArray("results");

            for (int k = 0; k <= MoviesViewAdapter.QUANTITY_VIEWS; k++) {

                JSONObject movieJson = results.getJSONObject(k);

                String movieId = movieJson.getString("id");
                String title = movieJson.getString("title");
                String description = movieJson.getString("overview");
                String releaseDate = movieJson.getString("release_date");
                String imageUrl = NetworkUtils.getPosterUrl(movieJson.getString("poster_path"));
                String ratings = movieJson.getString("vote_average");


                MoviesObject currentMovie = new MoviesObject(title, description, releaseDate, imageUrl, ratings, movieId);
                movieList.add(currentMovie);

            }

        } catch (JSONException e) {
            Log.e(TAG, "Error while parsing the Json Response");
            return null;
        }

        return movieList;
    }

    public static String[] getTrailers(String movieId) {

        String url = getVideoUrl(movieId);
        String rawJson = makeHttpRequest(url);

        String[] content = new String[3];

        try {
            JSONObject jsonRoot = new JSONObject(rawJson);
            JSONArray results = jsonRoot.getJSONArray("results");

            for(int i = 0; i < content.length; i ++) {

                JSONObject videoJson = results.getJSONObject(i);

                if(videoJson != null) {
                    String baseUrl = "https://www.youtube.com/watch?v=";
                    String key = videoJson.getString("key");
                    content[i] = baseUrl +  key;
                }

            }
        } catch (JSONException e) {
            Log.e(TAG, "Error Loading the Videos");
        }
        return content;
    }


    public static String[] getAuthors(String movieId) {

        String url = getCommentUrl(movieId);
        String rawJson = makeHttpRequest(url);


        String[] content = new String[3];

        try {

            JSONObject jsonRoot = new JSONObject(rawJson);
            JSONArray results = jsonRoot.getJSONArray("results");

            for (int i = 0; i < content.length; i++) {

                JSONObject commentJson = results.getJSONObject(i);
                if(commentJson != null) {
                    String author = commentJson.getString("author");
                    content[i] = author;
                }

            }

        } catch (JSONException e) {
            Log.e(TAG, "Error Loading the Authors");
            return new String[] {"No authors were found"};
        }
        return content;
    }

    public static String[] getComments(String movieId) {

        String url = getCommentUrl(movieId);
        String rawJson = makeHttpRequest(url);
        String[] content = new String[3];

        try {
            JSONObject jsonRoot = new JSONObject(rawJson);
            JSONArray results = jsonRoot.getJSONArray("results");

            for (int i = 0; i < content.length; i++) {

                JSONObject commentJson = results.getJSONObject(i);
                if(commentJson != null) {
                    String comment = commentJson.getString("content");
                    content[i] =  comment;
                }
            }

        } catch (JSONException e) {
            Log.e(TAG, "Error Loading the Comments");
            return new String[] {"No comments were found"};
        }
        return content;
    }

    public static String getDuration(String movieId) {

        String url = getMovieUrl(movieId);
        String rawJson = makeHttpRequest(url);
        String content = null;

        try {
            JSONObject jsonRoot = new JSONObject(rawJson);
            content = jsonRoot.getString("runtime");
        } catch (JSONException e) {
            Log.e(TAG, "Error Loading the Duration");
            return "Failed to load: ";
        }

        return content;
    }

    private static String getPosterUrl(String path) {

        Uri.Builder absolutePath = new Uri.Builder()
                .scheme("http")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath(SIZE)
                .appendPath(path.replace("/", ""));

        return absolutePath.build().toString();
    }

    private static String getCommentUrl(String movieId) {

        Uri.Builder absolutePath = new Uri.Builder()
                .scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(movieId)
                .appendPath("reviews")
                .appendQueryParameter("api_key", BuildConfig.MOVIES_API_KEY);

        return absolutePath.build().toString();
    }

    private static String getVideoUrl(String movieId) {

        Uri.Builder absolutePath = new Uri.Builder()
                .scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(movieId)
                .appendPath("videos")
                .appendQueryParameter("api_key", BuildConfig.MOVIES_API_KEY);

        return absolutePath.build().toString();
    }

    private static String getMovieUrl(String movieId) {

        Uri.Builder absolutePath = new Uri.Builder()
                .scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(movieId)
                .appendQueryParameter("api_key", BuildConfig.MOVIES_API_KEY);

        return absolutePath.build().toString();
    }

    public static String getPopularMoviesUrl() {

        Uri.Builder absolutePath = new Uri.Builder()
                .scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath("popular")
                .appendQueryParameter("api_key", BuildConfig.MOVIES_API_KEY);

        return absolutePath.build().toString();
    }

    public static String getMostRatedMoviesUrl() {

        Uri.Builder absolutePath = new Uri.Builder()
                .scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath("top_rated")
                .appendQueryParameter("api_key", BuildConfig.MOVIES_API_KEY);

        return absolutePath.build().toString();
    }

}
