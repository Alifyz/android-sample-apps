package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alifyz on 10/30/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = convertView;
        if(rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(
                        R.layout.earquake_item_template, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitude = (TextView)rootView.findViewById(R.id.magnitude);
        magnitude.setText(currentEarthquake.getmMagnitude());

        TextView location = (TextView)rootView.findViewById(R.id.location);
        location.setText(currentEarthquake.getmLocation());

        //Getting and Formatting the String from the JSON Response into a Readable Format
        Date date = new Date(currentEarthquake.getmDate());
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String displayDate = dataFormat.format(date);


        TextView dateTextview = (TextView)rootView.findViewById(R.id.date);
        dateTextview.setText(displayDate);

        return  rootView;
    }
}
