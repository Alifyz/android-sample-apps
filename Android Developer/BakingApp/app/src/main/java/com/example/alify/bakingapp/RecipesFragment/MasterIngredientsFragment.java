package com.example.alify.bakingapp.RecipesFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alify.bakingapp.Adapters.RecyclerMasterDetailAdapter;
import com.example.alify.bakingapp.R;
import com.example.alify.bakingapp.StepsActivity;

import java.util.HashMap;

/**
 * Created by alify on 2/20/2018.
 */

public class MasterIngredientsFragment extends Fragment  {



    private RecyclerView mRecyclerView;
    private RecyclerMasterDetailAdapter mMasterAdapter;
    private HashMap<String, String> mSteps;

    public MasterIngredientsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredient_fragment_list, container, false);

        mRecyclerView  = (RecyclerView) rootView.findViewById(R.id.rv_recipe_fragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSteps = (HashMap<String, String>) this.getArguments().getSerializable("stepsInformation");

        mMasterAdapter = new RecyclerMasterDetailAdapter(getActivity(), mSteps);

        mRecyclerView.setAdapter(mMasterAdapter);


        return rootView;
    }


}
