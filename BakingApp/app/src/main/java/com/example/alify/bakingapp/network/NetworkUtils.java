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
import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * Helper method to grab the data from the Web
     * @return JSON String
     */
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

    /**
     * Extract the Recipe Information from the JSON provided
     * @param json - extracted using the OkHttp Library
     * @return - List of recipes
     * Dear Interviewer/Technical Recruiter: I swear that this function is only for education purposes.
     * Especially when we have Retrofit+Gson out there :)
     */
    public static List<RecipeObject> extractRecipe(String json) {
        String jsonResponse = json;
        List<RecipeObject> recipeList = new ArrayList<>();

        try {
            JSONArray jsonRoot = new JSONArray(jsonResponse);
            for (int i = 0; i < jsonRoot.length(); i++) {

                JSONObject jsonRecipe = jsonRoot.getJSONObject(i);
                HashMap<String, String> recipes = new HashMap<>();
                HashMap<String, String> ingredients = new HashMap<>();
                HashMap<String, String> steps = new HashMap<>();

                recipes.put("id", jsonRecipe.getString("id"));
                recipes.put("name", jsonRecipe.getString("name"));
                recipes.put("servings", jsonRecipe.getString("servings"));
                recipes.put("image", jsonRecipe.getString("image"));

                JSONArray jsonIngredients = jsonRecipe.getJSONArray("ingredients");
                for (int k = 0; k < jsonIngredients.length(); k++) {

                    JSONObject jsonIngredient = jsonIngredients.getJSONObject(k);
                    ingredients.put("quantity_" + k, jsonIngredient.getString("quantity"));
                    ingredients.put("measure_" + k, jsonIngredient.getString("measure"));
                    ingredients.put("ingredient_" + k, jsonIngredient.getString("ingredient"));
                }

                JSONArray jsonSteps = jsonRecipe.getJSONArray("steps");
                for (int j = 0; j < jsonSteps.length(); j++) {

                    JSONObject jsonStep = jsonSteps.getJSONObject(j);
                    steps.put("id_" + j, jsonStep.getString("id"));
                    steps.put("shortDescription_" + j, jsonStep.getString("shortDescription"));
                    steps.put("description_" + j, jsonStep.getString("description"));
                    steps.put("videoURL_" + j, jsonStep.getString("videoURL"));
                    steps.put("thumbnailURL_" + j, jsonStep.getString("thumbnailURL"));
                }

                RecipeObject recipe = new RecipeObject(recipes, ingredients, steps, jsonRecipe.getString("name"));
                recipeList.add(recipe);
            }
        } catch (JSONException e) {
            Log.e(TAG_NAME, "Error Parsing the JSON");
            return null;
        }
        return recipeList;
    }
}
