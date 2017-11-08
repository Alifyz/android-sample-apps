package com.alifyz.csbooking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alifyz on 11/7/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context,@NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rootView = convertView;
        if (rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.listview_template,
                    parent, false);
        }

        Book currentBook = getItem(position);

        ImageView bookCover = (ImageView) rootView.findViewById(R.id.book_thumbnail);
        TextView title = (TextView) rootView.findViewById(R.id.book_title);
        TextView author = (TextView) rootView.findViewById(R.id.book_author);
        TextView description = (TextView) rootView.findViewById(R.id.book_description);

        bookCover.setImageBitmap(currentBook.getmImageResource());
        title.setText(currentBook.getmTitle());
        author.setText(currentBook.getmAuthor());
        description.setText(currentBook.getmDescripton());

        return rootView;
    }
}
