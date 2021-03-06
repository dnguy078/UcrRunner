package com.murraycole.ucrrunner.view;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.murraycole.ucrrunner.R;

public class MapRunning extends BaseMapActivity {
    TextView currspeed;
    TextView duration;
    TextView distance;
    TextView calories;
    TextView avgspeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_running);
        currspeed = (TextView) findViewById(R.id.maprunning_currspeed_textview);
        duration = (TextView)findViewById(R.id.maprunning_duration_textview);
        distance = (TextView)findViewById(R.id.maprunning_dist_textview);
        calories = (TextView)findViewById(R.id.maprunning_cal_textview);
        avgspeed = (TextView)findViewById(R.id.maprunning_avgspeed_textview);


        final Button PauseRun = (Button) findViewById(R.id.maprunning_pause_textview);
        PauseRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PauseRun.getText().equals("Pause")) {
                    PauseRun.setText("Start");
                    mapInfo.pauseRoute();
                } else if (PauseRun.getText().equals("Start")) {
                    PauseRun.setText("Pause");
                    mapInfo.resumeRoute();
                }

            }
        });

        Button StopRun = (Button)findViewById(R.id.maprunning_stop_textview);
        StopRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapInfo.stopRoute(-1);
            }
        });

        setupLocationStatsListener();


        setUpMapIfNeeded();
    }

    private void setupLocationStatsListener() {
        locationStatsListener = new MapInformation.LocationStatsListener() {
            @Override
            public void onLocationUpdate(Location location) {
                currspeed.setText("Speed:\n" + String.valueOf(mapInfo.getCurrentSpeed())); //Shouldn't this be calling Mapinfo.getCurrentSpeed?
                duration.setText("Duration:\n"); //Timer Should be displayed somewhere else
                distance.setText("Distance:" + String.valueOf(mapInfo.getDistance()));
                calories.setText("Calories:\n" + String.valueOf(mapInfo.getCalories()));
                avgspeed.setText("Avg Speed:" + String.valueOf(mapInfo.getAverageSpeed()));

            }

        };
    }

    public MapInformation getMapInformation() {
        return mapInfo;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /* Attempting override */
    @Override
    protected void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            mMap.setMyLocationEnabled(true);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mapInfo = new MapInformation(mMap, locationStatsListener);
                mapInfo.startRoute();
            }
        }
    }
}
