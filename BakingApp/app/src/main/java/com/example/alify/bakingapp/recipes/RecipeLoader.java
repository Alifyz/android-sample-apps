package com.example.alify.bakingapp.recipes;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.alify.bakingapp.network.NetworkUtils;

import java.util.List;

/**
 * Created by alify on 2/11/2018.
 */

public class RecipeLoader extends AsyncTaskLoader<List<RecipeObject>> {

    public RecipeLoader(Context context) {
        super(context);
    }

    @Override
    public List<RecipeObject> loadInBackground() {
        String json = NetworkUtils.makeHttpRequest();
        List<RecipeObject> results = NetworkUtils.extractRecipe(json);
        return results;
    }
}
