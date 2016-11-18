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
import java.util.Date;
import java.util.Locale;

/**
 * Created by bergs on 11/16/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /* date format for displaying the date of an earthquake */
    private  final DateFormat DATE_FORMAT = new SimpleDateFormat("MMM d, yyyy", Locale.US);

    /* date format for displaying the time of day of an earthquake */
    private final DateFormat TIME_FORMAT = new SimpleDateFormat("h:mma", Locale.US);

    private final String LOCATION_OFFSET_INDICATOR = " of ";
    private final String NO_OFFSET_REPLACEMENT = "Near the";

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

        // get the location String from the current Earthquake and split it into the location offset
        // and primary location Strings
        String fullLocation = currentEarthquake.getLocation();
        String locationOffset;
        String primaryLocation;

        // if the full location contains a location offset split it and extract the offset and
        // primary locations
        if (fullLocation.contains(LOCATION_OFFSET_INDICATOR)) {
            String[] splitLocation = fullLocation.split(LOCATION_OFFSET_INDICATOR);
            locationOffset = getContext().getResources().getString(R.string.location_offset,
                    splitLocation[0]);
            primaryLocation = splitLocation[1];
        } else {
            // the full location does not contain an offset, so replace it with "Near the" and use
            // the full location for the primary location
            locationOffset = NO_OFFSET_REPLACEMENT;
            primaryLocation = fullLocation;
        }

        // find the text view in the layout for the location offset and set its text to the current
        // Earthquake's location offset
        TextView locationOffsetTextView = (TextView) listItemView.findViewById(
                R.id.earthquake_location_offset);
        locationOffsetTextView.setText(locationOffset);

        // find the text view in the layout for the primary location and set its text to the current
        // Earthquake's primary location
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(
                R.id.earthquake_primary_location);
        primaryLocationTextView.setText(primaryLocation);

        // get the time from the current Earthquake and convert it into a new Date object
        Date earthquakeDate = new Date(currentEarthquake.getTimeInMilliseconds());

        // find the text view in the layout for the date and set its text to the current
        // Earthquake's date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.earthquake_date);
        dateTextView.setText(DATE_FORMAT.format(earthquakeDate));

        // find the text view in the layout for the time and set its text to the current
        // Earthquake's time of day
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.earthquake_time);
        timeTextView.setText(TIME_FORMAT.format(earthquakeDate));

        return listItemView;
    }
}
