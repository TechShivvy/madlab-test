package com.example.gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText inputLocation, inputLatitude, inputLongitude;
    Button search, get;
    TextView outputLocation, outputLatitude, outputLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        inputLocation = findViewById(R.id.inputLocation);
        inputLatitude = findViewById(R.id.inputLatitude);
        inputLongitude = findViewById(R.id.inputLongitude);

        outputLocation = findViewById(R.id.outputLocation);
        outputLatitude = findViewById(R.id.outputLatitude);
        outputLongitude = findViewById(R.id.outputLongitude);

        search = findViewById(R.id.searchButton);
        get = findViewById(R.id.getLocationButton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locationName = inputLocation.getText().toString();
                if (locationName.equals("")) {
                    getCurrentLocation();
                } else {
                    try {
                        List<Address> address = geocoder.getFromLocationName(locationName, 1);
                        outputLatitude.setText("Latitude: " + String.valueOf(address.get(0).getLatitude()));
                        outputLongitude.setText("Longitude: " + String.valueOf(address.get(0).getLongitude()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<Address> address = geocoder.getFromLocation(Double.valueOf(inputLatitude.getText().toString()),Double.valueOf(inputLongitude.getText().toString()),1);
                    outputLocation.setText("Name: "+address.get(0).getAddressLine(0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                outputLatitude.setText(Double.toString(latitude));
                outputLongitude.setText(Double.toString(longitude));
                locationManager.removeUpdates(this);
                return;
            }
        };


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        return;
    }

}