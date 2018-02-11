package com.example.alify.bakingapp.recipes;

import java.util.Map;

/**
 * Created by alify on 2/9/2018.
 */

public class RecipeObject {

    private Map<String, String> mRecipes;
    private Map<String, String> mSteps;

    public RecipeObject(Map<String, String> mRecipes, Map<String, String> mSteps) {
        this.mRecipes = mRecipes;
        this.mSteps = mSteps;
    }

    public Map<String, String> getmRecipes() {
        return mRecipes;
    }

    public Map<String, String> getmSteps() {
        return mSteps;
    }
}
