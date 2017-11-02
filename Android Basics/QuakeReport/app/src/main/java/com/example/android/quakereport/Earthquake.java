package com.example.android.quakereport;

import android.util.Log;

/**
 * Created by Alifyz on 10/30/2017.
 */

public class Earthquake {

    private Double mMagnitude;
    private String mLocation;
    private long mDate;

    public Earthquake(Double mMagnitude, String mLocation, long mDate) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public Double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmDate() {
        return mDate;
    }

}
