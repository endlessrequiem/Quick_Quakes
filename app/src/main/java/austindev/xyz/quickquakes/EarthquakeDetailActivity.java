package austindev.xyz.quickquakes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class EarthquakeDetailActivity extends AppCompatActivity {

    SharedPreferences earthquakeLocationInfo;
    SharedPreferences earthquakeMagnitudeInfo;
    SharedPreferences earthquakeDateInfo;
    SharedPreferences earthquakeTimeInfo;
    SharedPreferences earthquakeUrlInfo;
    TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_detail_activity);



        TextView location = findViewById(R.id.location);
        TextView magnitude = findViewById(R.id.magnitude);
        TextView time = findViewById(R.id.currTime);
        TextView date = findViewById(R.id.currDate);
        Button linkButton = findViewById(R.id.linkButton);
        ProgressBar progressWheel = findViewById(R.id.progressWheel);

        progressWheel.setVisibility(View.INVISIBLE);

        earthquakeLocationInfo = getSharedPreferences(getString(R.string.location_key),Context.MODE_PRIVATE);
        earthquakeMagnitudeInfo = getSharedPreferences(getString(R.string.magnitude_key),Context.MODE_PRIVATE);
        earthquakeDateInfo = getSharedPreferences(getString(R.string.date_key), Context.MODE_PRIVATE);
        earthquakeTimeInfo = getSharedPreferences(getString(R.string.time_key), Context.MODE_PRIVATE);
        earthquakeUrlInfo = getSharedPreferences(getString(R.string.url_key), Context.MODE_PRIVATE);

        String currLocation  = earthquakeLocationInfo.getString(getString(R.string.location_key), getResources().getString(R.string.location_default));
        String currMagnitude = earthquakeMagnitudeInfo.getString(getString(R.string.magnitude_key), getResources().getString(R.string.magnitude_default));
        String currTime = earthquakeTimeInfo.getString(getString(R.string.time_key), getResources().getString(R.string.time_default));
        String currDate = earthquakeDateInfo.getString(getString(R.string.date_key), getResources().getString(R.string.date_default));
        String urlString = earthquakeUrlInfo.getString(getString(R.string.url_key), getResources().getString(R.string.url_default));

        location.setText(currLocation);
        magnitude.setText(currMagnitude);
        time.setText(currTime);
        date.setText(currDate);

        double magnitudeDouble = Double.parseDouble(currMagnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeInt = getMagnitudeColor(magnitudeDouble);

        magnitudeCircle.setColor(magnitudeInt);
        getMagnitudeDescription(magnitudeDouble);

        linkButton.setOnClickListener(v -> {
            Uri earthquakeLink = Uri.parse(urlString);
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeLink);
            startActivity(websiteIntent);
            progressWheel.setVisibility(View.VISIBLE);
            finish();
        });

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

    public void getMagnitudeDescription(double magnitudeDouble) {
        int magnitudeDescFloor = (int) Math.floor(magnitudeDouble);
        description = findViewById(R.id.description);
        switch (magnitudeDescFloor) {
            case 0:
            case 1:
                description.setText(getString(R.string.one));
                break;
            case 2:
                description.setText(getString(R.string.two));
                break;
            case 3:
                description.setText(getString(R.string.three));
                break;
            case 4:
                description.setText(getString(R.string.four));
                break;
            case 5:
                description.setText(getString(R.string.five));
                break;
            case 6:
                description.setText(getString(R.string.six));
                break;
            case 7:
                description.setText(getString(R.string.seven));
                break;
            case 8:
                description.setText(getString(R.string.eight));
                break;
            case 9:
                description.setText(getString(R.string.nine));
                break;
            default:
                description.setText(getString(R.string.defScale));
                break;
        }

    }


    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        this.overridePendingTransition(0, 0);
        return true;
    }
}
