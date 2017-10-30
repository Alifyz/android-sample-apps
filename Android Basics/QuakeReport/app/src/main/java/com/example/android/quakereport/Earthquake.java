package com.example.android.quakereport;

/**
 * Created by Alifyz on 10/30/2017.
 */

public class Earthquake {

    private int mMagnitude;
    private int mLocation;
    private int mDate;

    public Earthquake(int mMagnitude, int mLocation, int mDate) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public int getmMagnitude() {
        return mMagnitude;
    }

    public int getmLocation() {
        return mLocation;
    }

    public int getmDate() {
        return mDate;
    }
}
