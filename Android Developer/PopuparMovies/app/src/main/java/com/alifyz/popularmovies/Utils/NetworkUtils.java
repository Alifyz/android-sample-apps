package com.alifyz.popularmovies.Utils;
import android.net.Uri;
import android.util.Log;

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
                String title = movieJson.getString("title");
                String description = movieJson.getString("overview");
                String releaseDate = movieJson.getString("release_date");
                String imageUrl = NetworkUtils.getPosterUrl(movieJson.getString("poster_path"));
                String ratings = movieJson.getString("vote_average");

                MoviesObject currentMovie = new MoviesObject(title,description,releaseDate,imageUrl, ratings);
                movieList.add(currentMovie);

            }

        } catch (JSONException e) {
            Log.e("NetworkUtils","Error while parsing the Json Response");
            return null;
        }

        Log.i("MovieList", movieList.toString());
        return movieList;

    }

    public static String getPosterUrl(String path) {

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
}
