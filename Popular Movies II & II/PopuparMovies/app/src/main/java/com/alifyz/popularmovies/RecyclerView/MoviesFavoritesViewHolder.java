package com.alifyz.popularmovies.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alifyz.popularmovies.R;

/**
 * Created by alify on 1/21/2018.
 */

public class MoviesFavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView iv_movies;

    public MoviesFavoritesViewHolder(View itemView) {
        super(itemView);
        iv_movies = (ImageView) itemView.findViewById(R.id.iv_movies);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int moviePosition = getAdapterPosition();
        MoviesViewCursorAdapter.mListener.onListItemClick(moviePosition);
    }
}
