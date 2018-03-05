package com.example.alify.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alify.bakingapp.R;
import com.example.alify.bakingapp.StepsActivity;

import java.util.HashMap;

/**
 * Created by alify on 2/14/2018.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.CustomViewHolder> {

    private HashMap<String, String> mRecipeDetails;
    private HashMap<String, String> mStepsDetails;

    private boolean isTabletEnable;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public IngredientAdapter(Context context, HashMap<String, String> data, HashMap<String, String> stepsData, boolean isTabletEnable) {
        this.mContext = context;
        this.mRecipeDetails = data;
        this.mStepsDetails = stepsData;
        this.isTabletEnable = isTabletEnable;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.ingredients_list_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder,  int position) {

        String ingredientName = mRecipeDetails.get("ingredient_" + position);
        String ingredientQuantity = mRecipeDetails.get("quantity_" + position);
        String ingredientMeasure = mRecipeDetails.get("measure_" + position);

        final int ingredientPosition = position;

        holder.mRecipeName.setText(ingredientName);
        holder.mRecipeQuantity.setText(ingredientQuantity);
        holder.mRecipeMeasure.setText(ingredientMeasure);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTabletEnable) {
                   /* Intent stepsIntent = new Intent(mContext, StepsActivity.class);

                    stepsIntent.putExtra("id_position", ingredientPosition);
                    stepsIntent.putExtra("stepsInfo", mStepsDetails);

                    stepsIntent.putExtra("videoUrl", mStepsDetails.get("videoURL_"  + ingredientPosition));
                    stepsIntent.putExtra("description", mStepsDetails.get("description_" + ingredientPosition));
                    stepsIntent.putExtra("thumbnailURL", mStepsDetails.get("thumbnailURL_" + ingredientPosition));


                    mContext.startActivity(stepsIntent);*/
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRecipeDetails.size() / 3;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView mRecipeName;
        TextView mRecipeQuantity;
        TextView mRecipeMeasure;

        public CustomViewHolder(View itemView) {
            super(itemView);

            mRecipeName = (TextView) itemView.findViewById(R.id.ingredient_name);
            mRecipeQuantity = (TextView) itemView.findViewById(R.id.ingredient_quantity);
            mRecipeMeasure = (TextView) itemView.findViewById(R.id.ingredient_measure);
        }
    }
}

