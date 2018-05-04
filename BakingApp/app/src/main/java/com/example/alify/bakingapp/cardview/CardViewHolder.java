package com.example.alify.bakingapp.cardview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alify.bakingapp.R;

/**
 * Created by alify on 2/12/2018.
 */

public class CardViewHolder extends RecyclerView.ViewHolder {

    public CardView mCardView;
    public ImageView mRecipeCard;
    public TextView mRecipeTitle;

    public CardViewHolder(View itemView) {
        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.cv_main);
        mRecipeCard = (ImageView) itemView.findViewById(R.id.iv_recipe_card);
        mRecipeTitle = (TextView) itemView.findViewById(R.id.tv_recipe_title);
    }
}
