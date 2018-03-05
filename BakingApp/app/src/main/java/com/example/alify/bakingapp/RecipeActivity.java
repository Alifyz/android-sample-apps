package com.example.alify.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alify.bakingapp.fragments.StepsFragment;
import com.example.alify.bakingapp.recipes.RecipeObject;

public class RecipeActivity extends AppCompatActivity {


    private RecipeObject mRecipeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_rootview);

        Intent mRecipeInfo = getIntent();
        mRecipeItem = mRecipeInfo.getParcelableExtra("recipe");
        setTitle(mRecipeInfo.getStringExtra("title"));

        Bundle mDataSet = new Bundle();

        mDataSet.putParcelable("recipe", mRecipeItem);

        StepsFragment mStepsFragment = new StepsFragment();
        mStepsFragment.setArguments(mDataSet);

        //Prevents Duplication of Fragments
        if(savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.steps_fragment_container, mStepsFragment)
                    .commit();
        }

    }


}
