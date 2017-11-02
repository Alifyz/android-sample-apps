package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

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
        TextView magnitude = (TextView)rootView.findViewById(R.id.magnitude);

        // Sete a cor de fundo apropriada no círculo de magnitude.
        // Busque o fundo do TextView, que é um GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Obtenha a cor de fundo apropriada baseada na magnitude do terremoto atual
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        magnitudeCircle.setColor(magnitudeColor);

        DecimalFormat magFormater = new DecimalFormat("0.0");
        String currentMag = magFormater.format(currentEarthquake.getmMagnitude());


        magnitude.setText(currentMag);

        //If the Raw Location contains the preposition of we create a split version
        //containing the near location in the index zero and the precise location on the
        //index 1. Then we create two textViews and use the set Method to the data we collect trough the method
        if(currentEarthquake.getmLocation().contains("of")) {
            String[] locations = currentEarthquake.getmLocation().split("of");

            TextView nearLocation = (TextView) rootView.findViewById(R.id.location_offset);
            TextView location = (TextView)rootView.findViewById(R.id.primary_location);

            nearLocation.setText(locations[0]);
            location.setText(locations[1]);

        } else {
            String[] locations = {"Near to", currentEarthquake.getmLocation()};
            TextView nearLocation = (TextView) rootView.findViewById(R.id.location_offset);
            TextView location = (TextView)rootView.findViewById(R.id.primary_location);

            nearLocation.setText(locations[0]);
            location.setText(locations[1]);
        }



        //Getting and Formatting the String from the JSON Response into a Readable Format
        Date date = new Date(currentEarthquake.getmDate());
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String displayDate = dataFormat.format(date);

        TextView dateTextview = (TextView)rootView.findViewById(R.id.time);
        dateTextview.setText(displayDate);

        return  rootView;
    }


    public int getMagnitudeColor(Double getMagnitude) {

        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(getMagnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
