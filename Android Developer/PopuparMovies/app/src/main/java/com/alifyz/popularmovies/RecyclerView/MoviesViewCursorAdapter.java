package com.alifyz.popularmovies.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alifyz.popularmovies.Database.MoviesContract;
import com.alifyz.popularmovies.R;
import com.squareup.picasso.Picasso;

/**
 * Created by alify on 1/21/2018.
 */

public class MoviesViewCursorAdapter extends RecyclerView.Adapter<MoviesViewHolder> {

    private Context mContext;
    private Cursor mData;
    static clickListener mListener;

    public MoviesViewCursorAdapter(Context mContext, Cursor mData, clickListener listener) {
        this.mContext = mContext;
        this.mData = mData;
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
        mData.moveToPosition(position);

        int imageIndexColumn = mData.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_IMAGE);
        String imagePath = mData.getString(imageIndexColumn);

        Picasso.with(mContext).load(imagePath).into(currentPosterImage);
    }

    @Override
    public int getItemCount() {
        return mData.getCount();
    }

    public interface clickListener {
        void onListItemClick(int clickedItem);
    }
}
