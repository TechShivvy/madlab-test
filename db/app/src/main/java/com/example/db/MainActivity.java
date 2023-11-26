package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper helper = new DBHelper(this);

    private EditText name;
    private EditText age;
    private EditText course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void handleClick(View view) {
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        course=findViewById(R.id.course);
        if (name.getText().toString().equals("") || age.getText().toString().equals("") || course.getText().toString().equals("")) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this,helper.addUser(name.getText().toString(),Integer.valueOf(age.getText().toString()),course.getText().toString()),Toast.LENGTH_LONG).show();

        List<List<String>> list =  new ArrayList<>();

        list = helper.readUsers("course = 'CSE'");

        for (List<String> userData : list) {
            System.out.println("User Data:");

            System.out.println("Name: " + userData.get(0));
            System.out.println("Age: " + userData.get(1));
            System.out.println("Course: " + userData.get(2));
        }

        Toast.makeText(this, helper.updateUser("Shajith","Hitesh",21,"EEE"), Toast.LENGTH_SHORT).show();


        Toast.makeText(this,helper.deleteUser("Hitesh"),Toast.LENGTH_LONG).show();
    }
}