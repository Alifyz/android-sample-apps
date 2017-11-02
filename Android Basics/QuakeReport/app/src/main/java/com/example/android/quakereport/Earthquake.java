package com.example.android.quakereport;

/**
 * Created by Alifyz on 10/30/2017.
 */

public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private long mDate;

    public Earthquake(String mMagnitude, String mLocation, long mDate) {
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

    public long getmDate() {
        return mDate;
    }
}
