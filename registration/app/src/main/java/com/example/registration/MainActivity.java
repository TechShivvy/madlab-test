package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private RadioGroup radioGroup;
    private CheckBox checkBoxReading, checkBoxTraveling, checkBoxCoding;
    private DatePicker datePickerDOB;
    private Spinner spinnerCountry;
    private Button btnSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        radioGroup = findViewById(R.id.radioGroup);
        checkBoxReading = findViewById(R.id.checkBoxReading);
        checkBoxTraveling = findViewById(R.id.checkBoxTraveling);
        checkBoxCoding = findViewById(R.id.checkBoxCoding);
        spinnerCountry = findViewById(R.id.spinner);
        datePickerDOB = findViewById(R.id.datePicker);
        btnSubmit = findViewById(R.id.submitButton);

        List<String> countries = new ArrayList<>();
        countries.add("India");
        countries.add("South Africa");
        countries.add("NZ");

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String gender =getSelectedRadioButton();
                List<String> interests =getSelectedInterests();
                String dob = getDOBFromDatePicker();
                String country = spinnerCountry.getSelectedItem().toString();

                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);

                intent.putExtra("Name",name1);
                intent.putExtra("Gender",gender);
                intent.putStringArrayListExtra("Interests", (ArrayList<String>) interests);
                intent.putExtra("DOB",dob);
                intent.putExtra("Country",country);

                startActivity(intent);
            }
        });

    }

    private String getDOBFromDatePicker() {
        return String.format("%02d/%02d/%d", datePickerDOB.getDayOfMonth(), datePickerDOB.getMonth()+1, datePickerDOB.getYear());
    }

    private List<String> getSelectedInterests() {
        List<String> selected = new ArrayList<>();
        if(checkBoxCoding.isChecked())
            selected.add(checkBoxCoding.getText().toString());
        if(checkBoxReading.isChecked())
            selected.add(checkBoxReading.getText().toString());
        if(checkBoxTraveling.isChecked())
            selected.add(checkBoxTraveling.getText().toString());
        return selected;
    }

    private String getSelectedRadioButton() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            return radioButton.getText().toString();
        }
        return "";
    }
}