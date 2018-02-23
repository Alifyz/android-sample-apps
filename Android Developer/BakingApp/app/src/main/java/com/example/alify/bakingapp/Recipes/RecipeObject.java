package com.example.alify.bakingapp.Recipes;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Created by alify on 2/9/2018.
 */

public class RecipeObject  {

    private HashMap<String, String> mRecipes;
    private HashMap<String, String> mIngredients;
    private HashMap<String, String> mSteps;

    public RecipeObject(HashMap<String, String> mRecipes, HashMap<String, String> mIngredients,HashMap<String, String> mSteps) {
        this.mRecipes = mRecipes;
        this.mIngredients = mIngredients;
        this.mSteps = mSteps;
    }

    public HashMap<String, String> getmRecipes() {
        return mRecipes;
    }

    public HashMap<String, String> getmSteps() {
        return mSteps;
    }

    public HashMap<String, String> getmIngredients() {
        return mIngredients;
    }

}
