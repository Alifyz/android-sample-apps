package com.example.alify.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class RecipeActivity extends AppCompatActivity {

    private HashMap<String, String> mData;
    private HashMap<String, String> mSteps;
    private HashMap<String, String> mIngredients;
    private String mRecipeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_listview_root);

        Intent mRecipeInfo = getIntent();
        mData =  (HashMap<String, String>) mRecipeInfo.getSerializableExtra("recipe");
        mSteps = (HashMap<String, String>) mRecipeInfo.getSerializableExtra("steps");
        mIngredients = (HashMap<String, String>) mRecipeInfo.getSerializableExtra("ingredients");
        mRecipeTitle = mData.get("name");

        setTitle(mRecipeTitle);
    }
}
