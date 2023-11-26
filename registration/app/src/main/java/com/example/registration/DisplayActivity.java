package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView textViewName = findViewById(R.id.name);
        TextView textViewGender = findViewById(R.id.gender);
        TextView textViewInterests = findViewById(R.id.interests);
        TextView textViewCountry = findViewById(R.id.country);
        TextView textViewDOB = findViewById(R.id.dob);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String gender = intent.getStringExtra("Gender");
        List<String> interests = intent.getStringArrayListExtra("Interests");
        String country = intent.getStringExtra("Country");
        String DOB = intent.getStringExtra("DOB");

        textViewName.setText(name);
        textViewGender.setText(gender);

        if(interests!=null && !interests.isEmpty())
        {
            StringBuilder text =new StringBuilder();
            for(String i:interests)
            {
                text.append(i).append(", ");
            }
            text.deleteCharAt(text.length() - 2);
            textViewInterests.setText(text.toString());
        }

        textViewDOB.setText(DOB);
        textViewCountry.setText(country);

    }
}