package com.example.alify.bakingapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.example.alify.bakingapp.recipes.RecipeObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by alify on 2/9/2018.
 */

public class NetworkUtils {

    private final static String TAG_NAME = NetworkUtils.class.getSimpleName();
    private final static String URL_JSON = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public static boolean isInternetOn(Context context) {
        ConnectivityManager cmManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cmManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    //Retrieve the Information from the URL
    public static String makeHttpRequest() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL_JSON)
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException e) {
            Log.e(TAG_NAME, e.toString());
            return null;
        }
    }

    public static List<RecipeObject> extractRecipe(String json) {
        String jsonResponse = json;

        try {
            JSONArray jsonRoot = new JSONArray(jsonResponse);

        }catch (JSONException e) {
            Log.e(TAG_NAME, "Error Parsing the JSON");
            return null;
        }

        return null;
    }





}
