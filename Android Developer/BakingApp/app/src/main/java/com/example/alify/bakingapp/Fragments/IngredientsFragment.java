package com.example.alify.bakingapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alify.bakingapp.Adapters.RecyclerViewAdapter;
import com.example.alify.bakingapp.R;

import java.util.HashMap;


/**
 * Created by alify on 2/15/2018.
 */

public class IngredientsFragment extends Fragment {

    private HashMap<String, String> mIngredients;
    private HashMap<String, String> mSteps;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    public IngredientsFragment() {}

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredient_fragment_list, container, false);

        mRecyclerView  = (RecyclerView) rootView.findViewById(R.id.rv_recipe_fragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mIngredients = (HashMap<String, String>)this.getArguments().getSerializable("data");
        mSteps =       (HashMap<String, String>)this.getArguments().getSerializable("steps");

        mAdapter = new RecyclerViewAdapter(getActivity(), mIngredients, mSteps);

        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }



}
