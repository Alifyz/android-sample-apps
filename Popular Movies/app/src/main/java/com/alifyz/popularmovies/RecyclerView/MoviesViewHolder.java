package com.alifyz.popularmovies.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alifyz.popularmovies.R;

/**
 * Created by Alifyz on 12/5/2017.
 */

public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView iv_movies;

    public MoviesViewHolder(View itemView) {
        super(itemView);
        iv_movies = (ImageView) itemView.findViewById(R.id.iv_movies);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int moviePosition = getAdapterPosition();
        MoviesViewAdapter.mListener.onListItemClick(moviePosition);
    }
}
