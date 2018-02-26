package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mazen on 2/26/2018.
 */
public class QuakeAdapter extends ArrayAdapter <Earthquake>{

    public QuakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context,0,earthquakes);
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
        magTextView.setText("" + currentQuake.getMag());

        TextView placeTextView = (TextView) listItemView.findViewById(R.id.place);
        placeTextView.setText(currentQuake.getPlace());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentQuake.getDate());

        return listItemView;
    }
}
