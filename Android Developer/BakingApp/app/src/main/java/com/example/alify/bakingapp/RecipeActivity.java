package com.example.alify.bakingapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.alify.bakingapp.Fragments.IngredientsFragment;

import java.util.HashMap;

public class RecipeActivity extends AppCompatActivity {

    private HashMap<String, String> mData;
    private HashMap<String, String> mIngredients;
    private HashMap<String, String> mSteps;
    private String mRecipeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ingredient_rootview);

        Intent mRecipeInfo = getIntent();
        mData =  (HashMap<String, String>) mRecipeInfo.getSerializableExtra("recipe");
        mIngredients = (HashMap<String, String>) mRecipeInfo.getSerializableExtra("ingredients");
        mSteps = (HashMap<String, String>) mRecipeInfo.getSerializableExtra("steps");

        mRecipeTitle = mData.get("name");
        setTitle(mRecipeTitle);

        Bundle mDataSet = new Bundle();
        mDataSet.putSerializable("data", mIngredients);
        mDataSet.putSerializable("steps", mSteps);

        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setArguments(mDataSet);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(savedInstanceState == null) {
            fragmentTransaction.add(R.id.ingreadient_fragment_container, ingredientsFragment);
            Toast.makeText(this, "Click in any item on the list for details", Toast.LENGTH_SHORT).show();
        }
        fragmentTransaction.commit();

    }


}
