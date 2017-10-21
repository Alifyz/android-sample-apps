package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {


    private int mBackGroundColor;

    public WordAdapter(Context context, ArrayList<Word> words, int mBackGroundColor) {
        super(context, 0, words);
        this.mBackGroundColor = mBackGroundColor;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.numbers_listview, parent, false);
        }

        final Word currentWord = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.TextView1);
        nameTextView.setText(currentWord.getDefautTranslation());

        TextView numberTextView = (TextView) listItemView.findViewById(R.id.TextView2);
        numberTextView.setText(currentWord.getMiwakTranslation());


        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.image);
        iconImageView.setImageResource(currentWord.getResourceImageId());

        int color = ContextCompat.getColor(getContext(), mBackGroundColor);

        LinearLayout parentLayout = (LinearLayout) listItemView.findViewById(R.id.listView);
        parentLayout.setBackgroundColor(color);

        if (currentWord.getResourceImageId() != 0) {
            iconImageView.setVisibility(View.VISIBLE);
        } else {
            iconImageView.setVisibility(View.GONE);
        }



        return listItemView;
    }
}
