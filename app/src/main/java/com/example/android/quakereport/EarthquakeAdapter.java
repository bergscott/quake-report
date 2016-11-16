package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by bergs on 11/16/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        // inflate the xml layout if needed
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,
                    parent, false);
        }

        // get the current Earthquake from the ArrayList
        Earthquake currentEarthquake = getItem(position);

        // find the text view in the layout for the magnitude and set its text to the current
        // Earthquake's magnitude
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.earthquake_magnitude);
        magnitudeTextView.setText(String.valueOf(currentEarthquake.getMagnitude()));

        // find the text view in the layout for the location and set its text to the current
        // Earthquake's location
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.earthquake_location);
        locationTextView.setText(currentEarthquake.getLocation());

        // find the text view in the layout for the date and set its text to the current
        // Earthquake's date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.earthquake_date);
        dateTextView.setText(dateFormat.format(currentEarthquake.getDate()));

        return listItemView;
    }
}