package com.example.android.quakereport;

/**
 * Created by Alifyz on 10/30/2017.
 */

public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private String mDate;

    public Earthquake(String mMagnitude, String mLocation, String mDate) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDate() {
        return mDate;
    }
}
