package com.example.alify.bakingapp.CardView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alify.bakingapp.R;
import com.example.alify.bakingapp.RecipeActivity;
import com.example.alify.bakingapp.Recipes.RecipeObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alify on 2/12/2018.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<RecipeObject> mRecipes;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public CardViewAdapter(List<RecipeObject> mRecipes, Context context) {
        this.mRecipes = mRecipes;
        this.mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recipe_list_cardview, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {

        final HashMap<String, String> recipe = mRecipes.get(position).getmRecipes();
        final HashMap<String, String> steps = mRecipes.get(position).getmSteps();
        final HashMap<String, String> ingredients = mRecipes.get(position).getmIngredients();

        holder.mRecipeTitle.setText(recipe.get("name"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recipeIntent = new Intent(mContext, RecipeActivity.class);

                recipeIntent.putExtra("recipe", recipe);
                recipeIntent.putExtra("steps", steps);
                recipeIntent.putExtra("ingredients", ingredients);

                mContext.startActivity(recipeIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
