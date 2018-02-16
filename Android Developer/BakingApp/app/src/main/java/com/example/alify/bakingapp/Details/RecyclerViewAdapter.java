package com.example.alify.bakingapp.Details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alify.bakingapp.R;

import java.util.HashMap;

/**
 * Created by alify on 2/14/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private HashMap<String, String> mRecipeDetails;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RecyclerViewAdapter(Context context, HashMap<String, String> data) {
        this.mContext = context;
        this.mRecipeDetails = data;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.ingredients_list_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        String ingredientName = mRecipeDetails.get("ingredient_" + position);
        String ingredientQuantity = mRecipeDetails.get("quantity_" + position);
        String ingredientMeasure = mRecipeDetails.get("measure_" + position);

        holder.mRecipeName.setText(ingredientName);
        holder.mRecipeQuantity.setText(ingredientQuantity);
        holder.mRecipeMeasure.setText(ingredientMeasure);
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

            mRecipeName = (TextView)  itemView.findViewById(R.id.ingredient_name);
            mRecipeQuantity = (TextView) itemView.findViewById(R.id.ingredient_quantity);
            mRecipeMeasure = (TextView)  itemView.findViewById(R.id.ingredient_measure);
        }
    }
}

