package com.example.location;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    EditText loc_ipt, lat_ipt, lon_ipt;
    Button search_btn, search_loc;
    TextView lat_val, lon_val, write_loc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        loc_ipt = findViewById(R.id.loc_ipt);
        search_btn = findViewById(R.id.search_btn);
        lat_val = findViewById(R.id.lat_val);
        lon_val = findViewById(R.id.lon_val);

        lat_ipt = findViewById(R.id.lat_ipt);
        lon_ipt = findViewById(R.id.lon_ipt);
        search_loc = findViewById(R.id.search_loc);
        write_loc = findViewById(R.id.write_loc);


        search_btn.setOnClickListener(v -> {
            String locationName = loc_ipt.getText().toString();
            if (locationName.isEmpty()) {
                // Get current location if loc_ipt is empty
                Log.d("Empty","YESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                getLocation();
            } else {
                try {
                    List<Address> addresses = geocoder.getFromLocationName(locationName, 1);
                    lat_val.setText("Latitude : " + String.valueOf((float) addresses.get(0).getLatitude()));
                    lon_val.setText("Longitude : " + String.valueOf((float) addresses.get(0).getLongitude()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        search_loc.setOnClickListener(v -> {
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(Double.valueOf(lat_ipt.getText().toString()), Double.valueOf(lon_ipt.getText().toString()), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            write_loc.setText("Latitude : " + addresses.get(0).getAddressLine(0));
        });
    }

    private void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Log.d("Latitude",Double.toString(latitude));
                lat_val.setText("Latitude: " + latitude);
                lon_val.setText("Longitude: " + longitude);
            }
        };

        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
}