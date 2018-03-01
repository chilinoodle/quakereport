package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mazen on 2/26/2018.
 */
public class Earthquake {
    private double mMag;
    private long mTimeInMilliseconds;
    private String mPlace;

    public Earthquake(double mag, String place, long timeInMilliseconds) {
        mMag = mag;
        mPlace = place;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public double getMag() {
        return mMag;
    }

    public String getPlace() {
        return mPlace;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }



}
