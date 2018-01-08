package com.alifyz.popularmovies.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alifyz.popularmovies.R;
import com.alifyz.popularmovies.Utils.MoviesObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Alifyz on 12/5/2017.
 */

public class MoviesViewAdapter extends RecyclerView.Adapter<MoviesViewHolder> {

    private Context mContext;
    private List<MoviesObject> mMovieData;
    public final static int QUANTITY_VIEWS = 11;

    static clickListener mListener;

    public MoviesViewAdapter(Context mContext, List<MoviesObject> data, clickListener listener) {
        this.mContext = mContext;
        this.mMovieData = data;
        this.mListener = listener;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movies, parent, false);
        MoviesViewHolder viewHolder = new MoviesViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {

        ImageView currentPosterImage = holder.iv_movies;
        String imagePath = mMovieData.get(position).getmMovieImage();

        Picasso.with(mContext).load(imagePath).into(currentPosterImage);

    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }

    public interface clickListener {
        void onListItemClick(int clickedItem);
    }
}
