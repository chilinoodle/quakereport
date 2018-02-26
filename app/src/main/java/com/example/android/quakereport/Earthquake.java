package com.example.android.quakereport;

/**
 * Created by Mazen on 2/26/2018.
 */
public class Earthquake {
    private float mMag;
    private String mDate;
    private String mPlace;

    public Earthquake(float mag, String place, String date) {
        mMag = mag;
        mPlace = place;
        mDate = date;
    }

    public float getMag() {
        return mMag;
    }

    public String getPlace() {
        return mPlace;
    }

    public String getDate() {
        return mDate;
    }

}
