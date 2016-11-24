package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * {@link EarthquakeLoader} is a simple {@link AsyncTaskLoader} that fetches a list of earthquakes
 * from the USGS earthquake API
 */

class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Query url for USGS earthquake api */
    private String mUrl;

    /**
     * Create a new EarthquakeLoader
     * @param context calling context with {@link android.app.LoaderManager.LoaderCallbacks}
     *                implementation
     * @param url query address for USGS earthquake API
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    /**
     * This is on a background thread
     * @return List of earthquakes resulting from api query
     */
    @Override
    public List<Earthquake> loadInBackground() {
        // if mUrl is empty, return early
        if (TextUtils.isEmpty(mUrl)) {
            return null;
        }
        // fetch list of earthquakes from mUrl http request
        return QueryUtils.extractEarthquakes(mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
