package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class QuakeAdapter extends ArrayAdapter <Earthquake>{

    private static final String LOCATION_SEPARATOR = " of ";

    public QuakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context,0,earthquakes);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    // formatting magnitude to a one-decimal number using class DecimalFormat
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int intMag = (int) Math.floor(magnitude);
        int magnitudeColorResourceID;
        switch (intMag) {
            case 0:
            case 1:
                magnitudeColorResourceID = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceID = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceID = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceID = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceID = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceID = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceID = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceID = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceID = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceID = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceID);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentQuake = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);

        //Setting text to formatted magnitude using formatMagnitude helper method we defined above
        magTextView.setText(formatMagnitude(currentQuake.getMag()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String currentPlace = currentQuake.getPlace();

        //Defined a LOCATION_SEPARATOR as " of " to split the currentPlace String to two strings
        String locationOffset;
        String primaryLocation;

        if (currentPlace.contains(LOCATION_SEPARATOR)) {
            String[] parts = currentPlace.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);//String near_the defined in strings.xml
            primaryLocation = currentPlace;
        }

        TextView mainPlaceTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        mainPlaceTextView.setText(locationOffset);

        TextView secPlaceTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        secPlaceTextView.setText(primaryLocation);

        /*
        /Taking the time in milliseconds and format it two times, once to date and once to hours
        / using two helper methods we defined at the beginning of the adapter
        */

        Date dateObject = new Date(currentQuake.getTimeInMilliseconds());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);




        return listItemView;
    }
}
