package com.example.alify.bakingapp.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alify.bakingapp.recipes.RecipeObject;

import java.util.List;

/**
 * Created by alify on 2/12/2018.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<RecipeObject> mRecipes;
    private LayoutInflater mLayoutInflater;

    public CardViewAdapter(List<RecipeObject> mRecipes, Context context) {
        this.mRecipes = mRecipes;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
