package com.example.alify.bakingapp.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alify.bakingapp.Adapters.RecyclerMasterDetailAdapter;
import com.example.alify.bakingapp.R;
import com.example.alify.bakingapp.StepsActivity;

import java.util.HashMap;

/**
 * Created by alify on 2/20/2018.
 */

public class MasterIngredientsFragment extends Fragment implements RecyclerMasterDetailAdapter.CustomCallBack {

    private RecyclerView mRecyclerView;
    private RecyclerMasterDetailAdapter mMasterAdapter;
    private HashMap<String, String> mSteps;

    private Context mStepsActivityContext;
    public MasterIngredientsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredient_fragment_list, container, false);

        mRecyclerView  = (RecyclerView) rootView.findViewById(R.id.rv_recipe_fragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSteps = (HashMap<String, String>) this.getArguments().getSerializable("stepsInformation");

        mMasterAdapter = new RecyclerMasterDetailAdapter(getActivity(), mSteps, this);
        mRecyclerView.setAdapter(mMasterAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mStepsActivityContext = context;
    }

    @Override
    public void setInformation(String text, String urlVideo) {
        StepsActivity hostActivity = (StepsActivity)getActivity();
        hostActivity.updateRecipeInstructions(text);
        hostActivity.setNewVideo(urlVideo, mStepsActivityContext);
    }
}
