package com.example.alify.bakingapp.cardview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alify.bakingapp.network.NetworkUtils;
import com.example.alify.bakingapp.R;
import com.example.alify.bakingapp.RecipeActivity;
import com.example.alify.bakingapp.recipes.RecipeObject;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alify on 2/12/2018.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<RecipeObject> mRecipes;
    private RecipeObject mRecipeItem;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public CardViewAdapter(List<RecipeObject> mRecipes, Context context) {
        this.mRecipes = mRecipes;
        this.mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recipe_list_cardview, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {

        final HashMap<String, String> recipe = mRecipes.get(position).getmRecipes();

        //Dealing with Recipe Images, If there is no image load the default one.
        String mRecipeImage = recipe.get("image");
        if(mRecipeImage.equals("") || mRecipeImage.length() == 0) {
            Bitmap defaultImage = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.meal);
            holder.mRecipeCard.setImageBitmap(defaultImage);
        } else {
            Picasso.with(mContext).load(mRecipeImage).into(holder.mRecipeCard);
        }

        holder.mRecipeTitle.setText(recipe.get("name"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkUtils.isInternetOn(mContext)) {

                    Intent recipeIntent = new Intent(mContext, RecipeActivity.class);
                    mRecipeItem = mRecipes.get(holder.getAdapterPosition());

                    recipeIntent.putExtra("title", recipe.get("name"));
                    recipeIntent.putExtra("recipe", mRecipeItem);

                    mContext.startActivity(recipeIntent);
                }else {
                    Toast.makeText(mContext, "Error, no internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
