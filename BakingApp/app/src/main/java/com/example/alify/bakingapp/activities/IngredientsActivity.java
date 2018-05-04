package com.example.alify.bakingapp.activities;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alify.bakingapp.BakinWidgetApp;
import com.example.alify.bakingapp.fragments.IngredientsFragment;
import com.example.alify.bakingapp.R;
import com.example.alify.bakingapp.recipes.RecipeObject;

import java.util.HashMap;

public class IngredientsActivity extends AppCompatActivity {

    private RecipeObject mRecipeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_rootview);

        Intent mData = getIntent();
        mRecipeItem = mData.getParcelableExtra("recipe");

        Bundle mDataSet = new Bundle();
        mDataSet.putParcelable("recipe", mRecipeItem);

        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setArguments(mDataSet);

        //Prevents Duplication of Fragments
        if(savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.steps_fragment_container, ingredientsFragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_widget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_ingredients_menu:
                try {
                    updateWidgetData();
                    updateWidget();
                    Toast.makeText(this, "Widget was Updated", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "Error Updating the Widget", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private String extractIngredientsWidget(RecipeObject recipeObject) {
        HashMap<String, String> ingredientList = recipeObject.getmIngredients();
        int ingredientSize = ingredientList.size() / 3;
        String content = "";
        for(int i = 0; i < ingredientSize; i++) {
            content += ingredientList.get("ingredient_" + i) + "\n";
        }
        return content;
    }

    private String extractRecipeName(RecipeObject recipeObject) {
        return recipeObject.getmRecipeName();
    }

    private void updateWidgetData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Pref", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editPreference = preferences.edit();
        editPreference.putString("title", extractRecipeName(mRecipeItem));
        editPreference.putString("ingredients", extractIngredientsWidget(mRecipeItem));
        editPreference.apply();
    }

    private void updateWidget(){
        Intent updateWidget = new Intent(this, BakinWidgetApp.class);
        updateWidget.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        sendBroadcast(updateWidget);
    }
}
