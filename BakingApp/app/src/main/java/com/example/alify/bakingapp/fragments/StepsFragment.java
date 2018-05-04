package com.example.alify.bakingapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alify.bakingapp.activities.IngredientsActivity;
import com.example.alify.bakingapp.R;
import com.example.alify.bakingapp.recipes.RecipeObject;
import com.example.alify.bakingapp.StepsActivity;

import java.util.HashMap;

/**
 * Created by alify on 2/15/2018.
 */

public class StepsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StepsAdapter mAdapter;
    private RecyclerView.ItemDecoration mDividerItemDecoration;
    private LinearLayoutManager mLinearLayout;

    private RecipeObject mRecipeItem;
    private TextView mIngredientList;
    private boolean isTabletMode = false;

    public StepsFragment() { }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.steps_list_item, container, false);

        mRecipeItem = this.getArguments().getParcelable("recipe");
        mIngredientList = (TextView) rootView.findViewById(R.id.tv_steps_list_item);

        mIngredientList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isTabletMode) {
                    Intent listIntent = new Intent(getActivity(), IngredientsActivity.class);
                    listIntent.putExtra("recipe", mRecipeItem);
                    startActivity(listIntent);
                } else {
                    IngredientsFragment ingredientsFragment = new IngredientsFragment();
                    Bundle dataSet = new Bundle();
                    dataSet.putParcelable("recipe", mRecipeItem);

                    ingredientsFragment.setArguments(dataSet);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.instructions_container, ingredientsFragment)
                            .commit();

                }
            }
        });

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_steps);
        mLinearLayout = new LinearLayoutManager(getActivity());

        //Detect Master Detail Layout
        if (mIngredientList.getTag().toString() == getString(R.string.layout_tablet)) {
            isTabletMode = true;
        } else {
            isTabletMode = false;
        }

        //Adjust the RecyclerView
        mAdapter = new StepsAdapter(mRecipeItem.getmSteps(), getActivity(), isTabletMode);
        mRecyclerView.setLayoutManager(mLinearLayout);
        mRecyclerView.setAdapter(mAdapter);

        //Line Separator
        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayout.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        return rootView;
    }


    class StepsAdapter extends RecyclerView.Adapter<StepsViewHolder> {

        HashMap<String, String> mSteps;

        Context mContext;
        LayoutInflater mInflater;
        boolean isTabletEnable;

        View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "";
                if (isTabletEnable) {
                     id = String.valueOf(view.getTag().toString());

                    Bundle dataSet = new Bundle();
                    dataSet.putString("videoUrl", mSteps.get("videoURL_" + id));
                    dataSet.putString("description", mSteps.get("description_" + id));
                    //Make sense to use this Thumbnail only when we have more space on the screen.
                    dataSet.putString("thumbnailURL", mSteps.get("thumbnailURL_" + id));

                    InstructionsFragment instructionsFragment = new InstructionsFragment();
                    instructionsFragment.setArguments(dataSet);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.instructions_container, instructionsFragment)
                            .commit();

                } else {
                    id = String.valueOf(view.getTag().toString());
                    Intent stepsActivity = new Intent(getActivity(), StepsActivity.class);
                    stepsActivity.putExtra("stepsInfo", mSteps);

                    stepsActivity.putExtra("videoUrl", mSteps.get("videoURL_"  + id));
                    stepsActivity.putExtra("description", mSteps.get("description_" + id));
                    stepsActivity.putExtra("thumbnailURL", mSteps.get("thumbnailURL_" + id));
                    stepsActivity.putExtra("position", id);

                    startActivity(stepsActivity);
                }
            }
        };

        public StepsAdapter(HashMap<String, String> mSteps, Context mContext, boolean isTabletEnable) {
            this.mSteps = mSteps;
            this.mContext = mContext;
            this.isTabletEnable = isTabletEnable;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = mInflater.inflate(R.layout.steps_description, parent, false);
            StepsViewHolder mStepsViewHolder = new StepsViewHolder(rootView);
            return mStepsViewHolder;
        }

        @Override
        public void onBindViewHolder(StepsViewHolder holder, int position) {

            String mCurrentText = mSteps.get("shortDescription_" + position);
            holder.mStepsTextView.setText(mCurrentText);
            holder.mStepsTextView.setTag(holder.getAdapterPosition());
            holder.mStepsTextView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mSteps.size() / 5;
        }
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {

        TextView mStepsTextView;

        public StepsViewHolder(View itemView) {
            super(itemView);
            mStepsTextView = (TextView) itemView.findViewById(R.id.main_text);
        }

    }
}
