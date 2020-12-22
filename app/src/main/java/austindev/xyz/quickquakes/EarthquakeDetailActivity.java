package austindev.xyz.quickquakes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import austindev.xyz.quickquakes.EarthquakeAdapter;


public class EarthquakeDetailActivity extends AppCompatActivity {

    SharedPreferences earthquakeLocationInfo;
    SharedPreferences earthquakeMagnitudeInfo;
    SharedPreferences earthquakeDateInfo;
    SharedPreferences earthquakeTimeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_detail_activity);


        TextView location = findViewById(R.id.location);
        TextView magnitude = findViewById(R.id.magnitude);
        TextView time = findViewById(R.id.currTime);
        TextView date = findViewById(R.id.currDate);

        earthquakeLocationInfo = getSharedPreferences(getString(R.string.location_key),Context.MODE_PRIVATE);
        earthquakeMagnitudeInfo = getSharedPreferences(getString(R.string.magnitude_key),Context.MODE_PRIVATE);
        earthquakeDateInfo = getSharedPreferences(getString(R.string.date_key), Context.MODE_PRIVATE);
        earthquakeTimeInfo = getSharedPreferences(getString(R.string.time_key), Context.MODE_PRIVATE);


        String currLocation  = earthquakeLocationInfo.getString(getString(R.string.location_key), getResources().getString(R.string.location_default));
        String currMagnitude = earthquakeMagnitudeInfo.getString(getString(R.string.magnitude_key), getResources().getString(R.string.magnitude_default));
        String currTime = earthquakeTimeInfo.getString(getString(R.string.time_key), getResources().getString(R.string.time_default));
        String currDate = earthquakeDateInfo.getString(getString(R.string.date_key), getResources().getString(R.string.date_default));

        location.setText(currLocation);
        magnitude.setText(currMagnitude);
        time.setText(currTime);
        date.setText(currDate);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(Double.parseDouble(currMagnitude));
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

    }

    public int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(this, magnitudeColorResourceId);
    }


    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        this.overridePendingTransition(0, 0);
        return true;
    }
}
