import java.util.Date;

/**
 * Represents an earthquake event
 * Created by bergs on 11/16/2016.
 */

public class Earthquake {

    /* Magnitude of the earthquake */
    private double mMagnitude;

    /* City nearest epicenter of earthquake */
    private String mLocation;

    /* Date of the earthquake */
    private Date mDate;


    /**
     * Create a new Earthquake object
     *
     * @param magnitude the magnitude of the earthquake
     * @param location the city nearest to the epicenter of the earthquake
     * @param date the date of the earthquake
     */
    public Earthquake(double magnitude, String location, Date date) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mDate = date;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public Date getDate() {
        return mDate;
    }
}
