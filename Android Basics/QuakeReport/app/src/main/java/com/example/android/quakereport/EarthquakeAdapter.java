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

import java.text.DecimalFormat;
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

        DecimalFormat magFormater = new DecimalFormat("0.0");
        String currentMag = magFormater.format(currentEarthquake.getmMagnitude());

        TextView magnitude = (TextView)rootView.findViewById(R.id.magnitude);
        magnitude.setText(currentMag);

        //If the Raw Location contains the preposition of we create a split version
        //containing the near location in the index zero and the precise location on the
        //index 1. Then we create two textViews and use the set Method to the data we collect trough the method
        if(currentEarthquake.getmLocation().contains("of")) {
            String[] locations = currentEarthquake.getmLocation().split("of");

            TextView nearLocation = (TextView) rootView.findViewById(R.id.locationNear);
            TextView location = (TextView)rootView.findViewById(R.id.location);

            nearLocation.setText(locations[0]);
            location.setText(locations[1]);

        } else {
            String[] locations = {"Near to", currentEarthquake.getmLocation()};
            TextView nearLocation = (TextView) rootView.findViewById(R.id.locationNear);
            TextView location = (TextView)rootView.findViewById(R.id.location);

            nearLocation.setText(locations[0]);
            location.setText(locations[1]);
        }



        //Getting and Formatting the String from the JSON Response into a Readable Format
        Date date = new Date(currentEarthquake.getmDate());
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String displayDate = dataFormat.format(date);

        TextView dateTextview = (TextView)rootView.findViewById(R.id.date);
        dateTextview.setText(displayDate);

        return  rootView;
    }
}
