package com.example.alify.bakingapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alify.bakingapp.adapters.IngredientAdapter;
import com.example.alify.bakingapp.R;
import com.example.alify.bakingapp.recipes.RecipeObject;


/**
 * Created by alify on 2/15/2018.
 */

public class IngredientsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private IngredientAdapter mAdapter;
    private boolean isTabletEnable;

    private RecipeObject mRecipeItem;
    public IngredientsFragment() {}

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredient_fragment_list, container, false);

        mRecyclerView  = (RecyclerView) rootView.findViewById(R.id.rv_recipe_fragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle mData = this.getArguments();
        mRecipeItem = mData.getParcelable("recipe");

        if(mRecyclerView.getTag() == getString(R.string.layout_tablet)) {
            isTabletEnable = true;
        }else {
            isTabletEnable = false;
        }

        mAdapter = new IngredientAdapter(getActivity(), mRecipeItem.getmIngredients(), mRecipeItem.getmSteps(), isTabletEnable);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
