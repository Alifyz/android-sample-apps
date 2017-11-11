package com.alifyz.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Alifyz on 11/10/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;

        if(rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate
                    (R.layout.news_layout, parent, false);
        }

        News currentNews = getItem(position);

        TextView newsTitle = (TextView)rootView.findViewById(R.id.title);
        TextView newsDescription = (TextView) rootView.findViewById(R.id.description);
        TextView newsDate = (TextView) rootView.findViewById(R.id.date);
        TextView newsAuthor = (TextView) rootView.findViewById(R.id.author);
        TextView newsCategory = (TextView) rootView.findViewById(R.id.category);


        newsTitle.setText(currentNews.getmTitle());
        newsDescription.setText(currentNews.getmDescription());
        newsDate.setText(currentNews.getmDatePublication());
        newsAuthor.setText(currentNews.getmAuthor());
        newsCategory.setText(currentNews.getmCategory());

        return rootView;
    }
}
