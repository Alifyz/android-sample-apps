package com.example.alify.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alify.bakingapp.R;

import java.util.HashMap;

/**
 * Created by alify on 2/20/2018.
 */

public class RecyclerMasterDetailAdapter extends RecyclerView.Adapter<RecyclerMasterDetailAdapter.CustomViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private HashMap<String, String> mStepsDetails;


    public RecyclerMasterDetailAdapter(Context mContext, HashMap<String, String> dataSet) {
        this.mContext = mContext;
        this.mStepsDetails = dataSet;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.steps_list_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        String ingredientName = mStepsDetails.get("shortDescription_" + position);
        holder.mStepName.setText(ingredientName);

    }

    @Override
    public int getItemCount() {
        return mStepsDetails.size() / 5;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView mStepName;

        public CustomViewHolder(View itemView) {
            super(itemView);
            mStepName = (TextView) itemView.findViewById(R.id.tv_steps_list_item);
        }
    }





}
