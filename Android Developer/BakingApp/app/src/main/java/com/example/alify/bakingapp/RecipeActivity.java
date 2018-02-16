package com.example.alify.bakingapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alify.bakingapp.Details.RecyclerViewAdapter;
import com.example.alify.bakingapp.RecipesFragment.IngredientsFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity {

    private HashMap<String, String> mData;
    private HashMap<String, String> mSteps;
    private HashMap<String, String> mIngredients;
    private String mRecipeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ingredient_rootview);

        Intent mRecipeInfo = getIntent();
        mData =  (HashMap<String, String>) mRecipeInfo.getSerializableExtra("recipe");
        mSteps = (HashMap<String, String>) mRecipeInfo.getSerializableExtra("steps");
        mIngredients = (HashMap<String, String>) mRecipeInfo.getSerializableExtra("ingredients");
        mRecipeTitle = mData.get("name");
        setTitle(mRecipeTitle);

        Bundle mDataSet = new Bundle();
        mDataSet.putSerializable("data", mIngredients);

        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setArguments(mDataSet);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(savedInstanceState == null) {
            fragmentTransaction.add(R.id.ingreadient_fragment_container, ingredientsFragment);
        }
        fragmentTransaction.commit();


    }


}
