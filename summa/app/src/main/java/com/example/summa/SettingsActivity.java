package com.example.summa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings Page");

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if(name!=null)
            ((TextView)findViewById(R.id.output)).setText(name);
    }
}