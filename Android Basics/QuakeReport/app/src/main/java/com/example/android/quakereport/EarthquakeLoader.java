package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Alifyz on 11/6/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    public static String RAW_JSON = "";

    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    public List<Earthquake> loadInBackground() {
        URL parsedURL = EarthQuakeUtils.parseURL(EarthquakeActivity.URL_SERVER);
        try {
            RAW_JSON = EarthQuakeUtils.makeHTTPRequest(parsedURL);
        } catch (IOException e) {
            return null;
        }

        List<Earthquake> earthquakes = EarthQuakeUtils.extractEarthquakes(RAW_JSON);
        return earthquakes;
    }
}
