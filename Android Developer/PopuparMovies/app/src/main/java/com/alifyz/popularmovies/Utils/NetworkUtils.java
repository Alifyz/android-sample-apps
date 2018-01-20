package com.alifyz.popularmovies.Utils;
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
            Log.e("NetworkUtils", "Error while making the Http Request");
            return null;
        } catch (NullPointerException e) {
            Log.e("NetworkUtils", "Error with a null object while making the Http Request");
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

                //Get the Movie ID for extract the Comments and the Video Url
                String movieId = movieJson.getString("id");
                String title = movieJson.getString("title");
                String description = movieJson.getString("overview");
                String releaseDate = movieJson.getString("release_date");
                String imageUrl = NetworkUtils.getPosterUrl(movieJson.getString("poster_path"));
                String ratings = movieJson.getString("vote_average");

                String[] comments = getComments(movieId);
                String[] trailers = getTrailers(movieId);

                MoviesObject currentMovie = new MoviesObject(title,description,releaseDate,imageUrl, ratings, comments, trailers);
                movieList.add(currentMovie);

            }

        } catch (JSONException e) {
            Log.e("NetworkUtils","Error while parsing the Json Response");
            return null;
        }

        Log.i("MovieList", movieList.toString());
        return movieList;

    }

    private static String[] getTrailers(String movieId) {
        String url = getVideoUrl(movieId);
        String rawJson = makeHttpRequest(url);

        String[] content = null;

        try {

            JSONObject jsonRoot = new JSONObject(rawJson);
            JSONArray results = jsonRoot.getJSONArray("results");

            for(int i = 0; i < results.length(); i ++) {

                JSONObject videoJson = results.getJSONObject(i);
                String baseUrl = "https://www.youtube.com/watch?v=";
                String key = videoJson.getString("key");

                content = new String[results.length() * 2];
                content[i] = baseUrl +  key;
            }


        }catch (JSONException e) {
            Log.e("Parsing the videos", "Error trying to parse the videos");
            return null;
        }

        return content;
    }

    private static String[] getComments(String movieId) {

        String url = getCommentUrl(movieId);
        String rawJson = makeHttpRequest(url);

        String[] content = null;

        try {

            JSONObject jsonRoot = new JSONObject(rawJson);
            JSONArray results = jsonRoot.getJSONArray("results");

            for(int i = 0; i < results.length(); i ++) {

                JSONObject commentJson = results.getJSONObject(i);
                String author = commentJson.getString("author");
                String comment = commentJson.getString("content");

                content = new String[results.length() * 2];
                content[i] = author + "-" + comment;
            }


        } catch (JSONException e) {
            Log.e("Parsing the Comments", "Error Trying to Parse the Comments");
            return null;
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
                .appendPath(path.replace("/",""));

        String finalPath = absolutePath.build().toString();
        return finalPath;
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
}
