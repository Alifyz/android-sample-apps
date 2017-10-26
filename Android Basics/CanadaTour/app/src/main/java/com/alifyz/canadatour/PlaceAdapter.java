package com.alifyz.canadatour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alifyz F Pires on 10/25/2017.
 * This class implements the Custom ArrayAdapter
 */

public class PlaceAdapter extends ArrayAdapter<Place> {


    public PlaceAdapter(Context context, ArrayList<Place> places) {
        super(context, 0, places);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_container, parent, false);
        }

        final Place places = getItem(position);

        ImageView img = (ImageView) listItemView.findViewById(R.id.imageView);
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        TextView description = (TextView) listItemView.findViewById(R.id.description);

        img.setImageResource(places.getImageResourceId());
        title.setText(places.getTitle());
        description.setText(places.getDescription());

        return listItemView;

    }
}
